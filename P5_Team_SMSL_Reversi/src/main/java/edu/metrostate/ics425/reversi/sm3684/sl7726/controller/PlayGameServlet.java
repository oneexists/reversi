package edu.metrostate.ics425.reversi.sm3684.sl7726.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics425.reversi.sm3684.sl7726.model.Game;

/**
 * Servlet implementation class PlayGameServlet
 */
@WebServlet("/moveDisk")
public class PlayGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor
     */
    public PlayGameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data
		String quit = request.getParameter("quit");
		String loc = request.getParameter("loc");
		String showHint = request.getParameter("showHint");
		String hideHint = request.getParameter("hideHint");
		
		Game game = ( Game ) request.getSession().getAttribute("game");
			
		// verify hint
		if (showHint != null) {
			request.setAttribute("hint", true);
		} else if (hideHint != null) {
			request.setAttribute("hint", false);
		} else {
			// verify quit
			if (quit != null) { 
				request.getSession().setAttribute("game", new Game()); 
			} else {
				// action: take turn
				int locInt = Integer.parseInt(loc);
				boolean takeTurn = game.placeDisk(locInt);
				if (!takeTurn) {
					request.setAttribute("err", "Invalid move");
				} else {
					request.setAttribute("err", null);
				}
				if (game.passMove()) {
					request.setAttribute("pass", true);
				} else {
					request.setAttribute("pass", false);
				}
				
				// store
				request.getSession().setAttribute("game", game);
				
			}			
		}
		
		// forward
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
