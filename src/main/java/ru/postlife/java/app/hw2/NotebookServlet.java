package ru.postlife.java.app.hw2;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@Slf4j
@WebServlet(name = "NotebookServlet", urlPatterns = "/notebook")
public class NotebookServlet extends HttpServlet {
    private transient ServletConfig config;

    private static final int COUNT = 67;
    private static final Random RANDOM = new Random();
    private Notebook[] notebooks = new Notebook[COUNT];

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;

        for (int i = 0; i < COUNT; i++) {
            float rnd = (float) Math.random();
            notebooks[i] = new Notebook(generateBrand(rnd), generateRam(4, 20, 4), generatePrice(500, 2100, 100));
        }
    }

    // GET http://localhost:8080/java-ee/notebook?page=1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int page = Integer.parseInt(req.getParameter("page"));

        if (page * 10 > COUNT) {
            resp.sendRedirect(req.getContextPath() + "/notebook?page=0");
            getServletContext().getRequestDispatcher("NotebookServlet");
        }

        out.print("<html><body>");
        out.print("<h1>Notebooks</h1>");
        for (int i = page * 10; i < (page + 1) * 10; i++) {
            if (i < COUNT)
                out.println("<h3>" + String.format("%d. %s", i + 1, notebooks[i]) + "</h3>");
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public String getServletInfo() {
        return "NotebookServlet";
    }

    @Override
    public void destroy() {
        log.info("Servlet {} destroyed", getServletInfo());
    }

    public static Brand generateBrand(float rnd) {
        if (rnd < 0.2) {
            return Brand.LENOVO;
        } else if (rnd < 0.4) {
            return Brand.ASUS;
        } else if (rnd < 0.6) {
            return Brand.MACBOOK;
        } else if (rnd < 0.8) {
            return Brand.ACER;
        } else {
            return Brand.MSI;
        }
    }

    public static int generatePrice(int min, int max, int step) {
        return RANDOM.nextInt((max - min) / step) * step + min;
    }

    public static int generateRam(int min, int max, int step) {
        return RANDOM.nextInt((max - min) / step) * step + min;
    }
}
