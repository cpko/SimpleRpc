package com.cpk.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:cp
 * Created on 10/23/15.
 */
public class Rpc {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void export(final Object service, final int port) throws IOException {
        checkExport(service, port);

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            final Socket socket = serverSocket.accept();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ObjectInputStream ois = null;
                    ObjectOutputStream oos = null;
                    try {
                        ois = new ObjectInputStream(socket.getInputStream());
                        oos = new ObjectOutputStream(socket.getOutputStream());

                        String methodName = ois.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
                        Object[] parameters = (Object[]) ois.readObject();

                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, parameters);
                        oos.writeObject(result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        closeOutputStream(oos);
                        closeInputStream(ois);
                        closeSocket(socket);
                    }
                }
            });

        }

    }

    public <T> T refer(Class<T> interfaceClass, final String host, final int port) {
        checkRefer(interfaceClass, host, port);

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectOutputStream oos = null;
                ObjectInputStream ois = null;
                try {
                    socket = new Socket(host, port);
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeUTF(method.getName());
                    oos.writeObject(method.getGenericParameterTypes());
                    oos.writeObject(args);

                    ois = new ObjectInputStream(socket.getInputStream());
                    Object result = ois.readObject();
                    return result;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    closeInputStream(ois);
                    closeOutputStream(oos);
                    closeSocket(socket);
                }
            }
        });
    }

    private void checkExport(Object service, int port) {
        if (service == null) {
            throw new IllegalArgumentException("service can't be null");
        }
        checkPort(port);
    }

    private void checkRefer(Class interfaceClass, String host, int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("interface class can't be null");
        }
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("host can't be empty");
        }
        checkPort(port);
    }

    private void checkPort(int port) {
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("invalid port");
        }
    }

    private void closeInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                //ignore
            }
        }
    }

    private void closeOutputStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                //ignore
            }
        }
    }

    private void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                //ignore
            }
        }

    }


}
