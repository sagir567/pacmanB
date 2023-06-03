import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex3Algo implements PacManAlgo {
	private int _count;
	private int[][] _game;
	private GhostCL[] _ghosts;
	private Index2D _pacmanPosition;
	private int _code = 0;

	public Ex3Algo() {
		_count = 0;
	}

	@Override
	public String getInfo() {
		return "Improved Algorithm: Always moves towards the nearest accessible pellet.";
	}

	@Override
	public int move(PacmanGame game) {
		_pacmanPosition = stringToIndex(game.getPos(_code));
		_game = game.getGame(_code);
		_ghosts = game.getGhosts(_code);

		// Find the position of the nearest accessible pellet.
		Index2D nearestPellet = findNearestAccessiblePellet();

		// Determine which direction to move in order to get closer to the nearest pellet.
		int nextMove = decideNextMove(nearestPellet);

		return nextMove;
	}

	private Index2D findNearestAccessiblePellet() {
		Index2D nearestPellet = null;
		int minDistance = Integer.MAX_VALUE;
		for (int x = 0; x < _game.length; x++) {
			for (int y = 0; y < _game[0].length; y++) {
				if (_game[x][y] == 3 && isSafe(x, y)) {  // Pellets are represented by 3
					int distance = Math.abs(x - _pacmanPosition.getX()) + Math.abs(y - _pacmanPosition.getY());
					if (distance < minDistance) {
						minDistance = distance;
						nearestPellet = new Index2D(x, y);
					}
				}
			}
		}
		return nearestPellet;
	}

	private int decideNextMove(Index2D nearestPellet) {
		if (nearestPellet != null) {
			List<Integer> possibleMoves = new ArrayList<>();
			if (nearestPellet.getX() > _pacmanPosition.getX() && isSafe(_pacmanPosition.getX() + 1, _pacmanPosition.getY())) {
				possibleMoves.add(Game.RIGHT);
			}
			if (nearestPellet.getX() < _pacmanPosition.getX() && isSafe(_pacmanPosition.getX() - 1, _pacmanPosition.getY())) {
				possibleMoves.add(Game.LEFT);
			}
			if (nearestPellet.getY() > _pacmanPosition.getY() && isSafe(_pacmanPosition.getX(), _pacmanPosition.getY() - 1)) {
				possibleMoves.add(Game.DOWN);
			}
			if (nearestPellet.getY() < _pacmanPosition.getY() && isSafe(_pacmanPosition.getX(), _pacmanPosition.getY() + 1)) {
				possibleMoves.add(Game.UP);
			}
			if (!possibleMoves.isEmpty()) {
				return possibleMoves.get(new Random().nextInt(possibleMoves.size()));
			}
		}

		// If there are no accessible pellets or we are unable to move towards the nearest pellet, move randomly.
		return randomDir();
	}

	private boolean isSafe(int x, int y) {
		// Check if the next move is an obstacle (wall).
		if (_game[x][y] == 1) {
			return false;
		}

		// Check if there's a ghost in the next move.
		for (GhostCL ghost : _ghosts) {
			if (stringToIndex(ghost.getPos(_code)).equals(new Index2D(x, y))) {
				return false;
			}
		}
		return true;
	}

	private Index2D stringToIndex(String pos) {
		String[] splitPos = pos.split(",");
		int x = Integer.parseInt(splitPos[0].trim());
		int y = Integer.parseInt(splitPos[1].trim());
		return new Index2D(x, y);
	}

	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int) (Math.random() * dirs.length);
		return dirs[ind];
	}
}
