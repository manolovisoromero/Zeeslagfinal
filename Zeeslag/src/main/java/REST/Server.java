package REST;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Server {


    public static void main(String[] args) throws Exception {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8090);

        server.setHandler(getJerseyHandler());

        server.start();
        server.join();

    }

    private static Handler getJerseyHandler() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        handler.setContextPath("/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                LoginEndpoint.class.getCanonicalName());

        return handler;
    }
}


