package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;

import connectionFactory.Connections;
import dto.States;
import queryLibrary.Queries;

public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public JsonServlet() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String countryID = request.getParameter("countryName");
        String json = null;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        Map<Integer, String> mps = new HashMap<Integer, String>();
        
        String askStates = Queries.askStates+countryID;
        
        try {
			System.out.println("get connection");
			cn = Connections.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(askStates);
			while(rs.next()) {
				mps.put(rs.getInt(1), rs.getString(2));
			}
			
			json = new Gson().toJson(mps);
									
			response.setContentType("application/json");
            response.getWriter().write(json);
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
