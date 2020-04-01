package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import connectionFactory.Connections;
import queryLibrary.Queries;

public class JsonServletRoads extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public JsonServletRoads() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String statesID = request.getParameter("statesName");
        String jsonx = null;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        Map<Integer, String> mps = new HashMap<Integer, String>();
        
        String askRoads = Queries.askRoads+statesID;
        
        try {
			
			cn = Connections.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(askRoads);
			while(rs.next()) {
				mps.put(rs.getInt(1), rs.getString(2));
			}
			jsonx = new Gson().toJson(mps);
			
			response.setContentType("application/json");
            response.getWriter().write(jsonx);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

}
