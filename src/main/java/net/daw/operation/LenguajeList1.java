/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.LenguajeBean;
import net.daw.dao.LenguajeDao;
import net.daw.helper.Contexto;
import net.daw.helper.ParserJson;

/**
 *
 * @author Alvaro
 */
public class LenguajeList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/lenguaje/list.jsp");
        try {
            LenguajeDao oLenguajeDAO = new LenguajeDao(oContexto.getEnumTipoConexion());
            Integer intPages = oLenguajeDAO.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<LenguajeBean> listado = oLenguajeDAO.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());

            ParserJson oParserJson = new ParserJson();
            String strListado = oParserJson.listJson(listado);
            response.setHeader("Content-Type", "application/json");
            response.setHeader("Accept", "JSON");
            
            return strListado;
        } catch (Exception e) {
            throw new ServletException("LenguajeList1: View Error: " + e.getMessage());
        }
    }
}
