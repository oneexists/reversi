package edu.metrostate.ics425.reversi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics425.reversi.model.Game;

/**
 * Servlet implementation class PlayGameServlet
 */
@WebServlet("/moveDisk")
public class PlayGameServlet extends HttpServlet {
	private static final long serialVersionUID = 202206001L;
    
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
	
	/**
	 * Verifies whether user has requested to quit or pass their turn.
	 * If not, the user has decided to take a turn and their turn is
	 * verified. 
	 * 
	 * Invalid turns will set the error and update the last turn to 
	 * the previous turn.
	 * 
	 * After the turn has been verified, the board is evaluated to 
	 * determine whether the user is able to pass their turn and
	 * the game is saved before the request is forwarded.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quit = request.getParameter("quit");
		String loc = request.getParameter("loc");
		String previousTurn = request.getParameter("lastTurn");
		String pass = request.getParameter("pass");
		
		Game game = ( Game ) request.getSession().getAttribute("game");
			
		if (quit != null) {		// verify quit
			request.getSession().setAttribute("game", new Game()); 
		} else if (pass != null && pass.equals("true")) {	// verify pass
			game.nextPlayer();				
		} else {
			int locInt = Integer.parseInt(loc);
			request.setAttribute("lastTurn", game.getTurnString());
			boolean takeTurn = game.placeDisk(locInt);
			if (takeTurn) {
				// successful turn
				request.setAttribute("err", null);
			} else {	// invalid turn
				request.setAttribute("lastTurn", previousTurn);
				request.setAttribute("err", "Invalid move");				
			}
			// set pass move
			request.setAttribute("pass", game.passMove());
			
			// store game
			request.getSession().setAttribute("game", game);
			
		}
		
		// forward request
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
