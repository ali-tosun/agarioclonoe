package tr.org.linux.kamp.agar.logic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agar.model.Chip;
import tr.org.linux.kamp.agar.model.Dificulty;
import tr.org.linux.kamp.agar.model.Enemy;
import tr.org.linux.kamp.agar.model.GameObject;
import tr.org.linux.kamp.agar.model.Mine;
import tr.org.linux.kamp.agar.model.Player;
import tr.org.linux.kamp.agar.view.GameFrame;
import tr.org.linux.kamp.agar.view.GamePanel;
/**
 * 
 * @author ali
 * @version 1.0
 *
 */
public class GameLogic {
	
	private boolean isGamingRuning;
	private int xTarget;
	private int yTarget;
	private ArrayList<GameObject> chipsToRemove;
	private Player player;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> minesToRemove;
	private ArrayList<GameObject> enemiesToRemove;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Random random;
	
	
	/**
	 * 
	 * @param playerName 
	 * @param selectedColor
	 * @param dificulty
	 */
	public GameLogic(String playerName, Color selectedColor, Dificulty dificulty) {
		player = new Player(10, 10, 20, 1, selectedColor, playerName);
		enemiesToRemove = new ArrayList<GameObject>();
		chipsToRemove = new ArrayList<GameObject>();
		minesToRemove = new ArrayList<GameObject>();

		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(player);

		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObjects);
		random = new Random();

		switch (dificulty) {
		case HARD: //Oyuncunun hızını dusmandan az yap
			fillChips(100);
			fillMines(17);
			fillEnemies(15);

			break;

		case NORMAL:
			fillChips(50);
			fillMines(10);
			fillEnemies(6);

			break;

		case EASY:
			fillChips(40);
			fillMines(7);
			fillEnemies(5);

			break;

		default:
			break;
		}

		fillChips(100);
		fillMines(10);
		fillEnemies(4);
		addMouseEvents();
	}
	/**
	 * Control collisions.
	 *(GameObject gameObject : gameObjects) -->Controls the objects in the game.
	 *if (player.getRectangle().intersects(gameObject.getRectangle())) --> It looks at the intersection with an object.
	 *if (gameObject instanceof Chip) --> Controls the incoming objects


		
	 */
	private synchronized void checkCollions() {
		// contains hepsini içine aldıgında true sonuc dogurur.
		// ArrayList<GameObject> objectsToRemoves=new ArrayList<GameObject>();
		// //sonradan eklenen ben
		for (GameObject gameObject : gameObjects) {
			// if (player.getRectangle().intersects(gameObject.getRectangle()))
			// { sadece kesistiginde calısır...
			if (player.getRectangle().intersects(gameObject.getRectangle())) {
				if (gameObject instanceof Chip) {
					player.setRadius(player.getRadius()
							+ gameObject.getRadius());
					// gameObjects.remove(gameObject);
					chipsToRemove.add(gameObject);
					// objectsToRemoves.add(gameObject); //sonradan eklenen ben

				}
				if (gameObject instanceof Mine) {
					player.setRadius((int) player.getRadius() / 2);
					minesToRemove.add(gameObject);
				}

				if (gameObject instanceof Enemy) {
					if (player.getRadius() > gameObject.getRadius()) {
						player.setRadius((int) player.getRadius()
								+ gameObject.getRadius());
						enemiesToRemove.add(gameObject);
					}

					else if (player.getRadius() < gameObject.getRadius()) {
						gameObject.setRadius(gameObject.getRadius()
								+ player.getRadius());
						// game over
						isGamingRuning = false;
					}

				}

			}
			/**
			 * Enemy nin chip ve mine yeme durumları.
			 */
			if (gameObject instanceof Enemy) {
				Enemy enemy = (Enemy) gameObject;

				for (GameObject gameObject2 : gameObjects) {
					if (enemy.getRectangle().intersects(
							gameObject2.getRectangle())) {
						if (gameObject2 instanceof Chip) {

							enemy.setRadius(enemy.getRadius()
									+ gameObject2.getRadius());
							chipsToRemove.add(gameObject2);
						}
						if (gameObject2 instanceof Mine) {
							enemy.setRadius((int) enemy.getRadius() / 2);
							minesToRemove.add(gameObject2);
						}

					}

				}

			}
		}
		// yenilen yiyecek,mayın ve düşmanların ekrandan temizlenmesi.
		gameObjects.removeAll(minesToRemove);
		gameObjects.removeAll(chipsToRemove);
		gameObjects.removeAll(enemiesToRemove);

		// gameObjects.addAll(objectsToRemoves); sonradan

	}

	// Loop is complete,remove objects from the list..
	
	/**
	 * ekrana yeni objelerin eklenmesi.eklenecek objelerin sayısı , checkCollions metodundan gelmektedir.
	 * nesneler eklendikten sonra clear ile arrayList boşaltılır,boşaltılmazsa ; ilk chip yendikten sonra ekrana bir chip atar.
	 * ikinci chip yendikten sonra ekrana bu sefer bir yerine 2 tane chip atar.
	 * 
	 */
	private void addNewObject() {

		fillChips(chipsToRemove.size());
		// fillEnemies(enemiesToRemove.size());
		fillMines(minesToRemove.size());
		fillEnemies(enemiesToRemove.size());
		chipsToRemove.clear();
		enemiesToRemove.clear();
		minesToRemove.clear(); // tüm mınes leri temizler.temizlemessek
								// 10.dakikada 27.mayın olur :D
	}
	
	/**
	 * 
	 * @param n , ekrana kaç adet chip basılcagını saglayan degerimiz.Addnew Object methodundan gelir.
	 * Ekrana random olarak chip basmamızı saglar.
	 */
	private void fillChips(int n) {

		for (int i = 0; i < n; i++) {
			gameObjects.add(new Chip(random.nextInt(950), random.nextInt(950),
					10, Color.orange));
		}

	}

	// hata
	/**
	 * 
	 * @param n ekrana kaç adet mayın basılcagını saglayan degerimiz.Addnew Object methodundan gelir.
	 * Ekrana random olarak mayın basmamızı saglar.
	 * while (player.getRectangle().intersects(mine.getRectangle())) ile de ekrana basılcak random mayınlarının
	 * oyuncu içinde çıkmayacaının garantisinin verir.
	 * 
	 */
	 
	private void fillMines(int n) {
		for (int i = 0; i < n; i++) {

			Mine mine = new Mine(random.nextInt(950), random.nextInt(950), 20,
					Color.GREEN);

			while (player.getRectangle().intersects(mine.getRectangle())) { // interest
																			// kesisim
																			// kontrol
																			// eder.
				mine.setX(random.nextInt(950));
				mine.setY(random.nextInt(950));
			}
			gameObjects.add(mine);

		}
	}
	/**
	 * 
	 * @param n ekrana rastgele dusman basar.
	 * yeni eklencek dusmanlar , oyuncunun icinde dogmayacagını garanti eder.
	 */
	private void fillEnemies(int n) {
		for (int i = 0; i < n; i++) { // intersect dikkat !!!!!
			Enemy enemy = new Enemy(random.nextInt(950), random.nextInt(950),
					(random.nextInt(50) + 10), 2, Color.RED);
			while (player.getRectangle().intersects(enemy.getRectangle())) {
				enemy.setX(random.nextInt(950));
				enemy.setY(random.nextInt(950));

			}
			gameObjects.add(enemy);
		}
	}
	
	/**
	 * bizim hareketlermizi saglar.Xtarget mouse koordinatıdır.O degere gore hareket ederiz.
	 */
	private synchronized void movePlayer() {
		if (xTarget > player.getX()) {
			player.setX(player.getX() + player.getSpeed());

		} else if (xTarget < player.getX()) {
			player.setX(player.getX() - player.getSpeed());
		}

		if (yTarget > player.getY()) {
			player.setY(player.getY() + player.getSpeed());

		} else if (yTarget < player.getY()) {
			player.setY(player.getY() - player.getSpeed());
		}
	}
	
	
	/**
	 * Dusmanları hareketini tanımlarız.
	 * Puanları bizden dusuk ise kacarlar.Fazla ise bizi kovalarlar.
	 */
	private synchronized void moveEnemies() {

		for (GameObject gameObject : gameObjects) {  // GameObject gameObject : enemy olarak dene. yorum 1

			if (gameObject instanceof Enemy) {
				Enemy enemy = (Enemy) gameObject;
				if (enemy.getRadius() < player.getRadius()) {
					int distance = (int) Point.distance(player.getX(),
							player.getY(), enemy.getX(), enemy.getY());
					int newX = enemy.getX() + enemy.getSpeed();
					int newY = enemy.getY() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;

					}

					newX = enemy.getX() + enemy.getSpeed();
					newY = enemy.getX() - enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;

					}
					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getX() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;

					}

					newX = enemy.getX() - enemy.getSpeed();
					newY = enemy.getX() - enemy.getSpeed();
					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;

					}

					newX = enemy.getX() + enemy.getSpeed();
					newY = enemy.getX() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
						continue;

					}

				} else {
					// yiyecek

					if (player.getX() > enemy.getX()) {
						enemy.setX(enemy.getY() + enemy.getSpeed());

					} else if (player.getX() < enemy.getX()) {
						enemy.setX(enemy.getX() - enemy.getSpeed());
					}

					if (player.getY() > enemy.getY()) {
						enemy.setY(enemy.getY() + enemy.getSpeed());

					} else if (player.getY() < enemy.getY()) {
						enemy.setY(enemy.getY() - enemy.getSpeed());
					}

				}
			}

		}

	}
/**
 * Dusmanların kacıs guzergahlarının hesaplanması ıcın kullanılan metod.
 * @param enemy
 * @param distance
 * @param x
 * @param y
 * @return
 */
	private boolean calculateNewDistanceToEnemy(Enemy enemy, int distance,
			int x, int y) {

		int newDistance = (int) Point.distance(player.getX(), player.getY(), x,
				y);
		if (newDistance > distance) {
			enemy.setX(x);
			enemy.setY(y);
			return true;
		}
		return false;
	}

	private void startGame() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isGamingRuning) {
					movePlayer();
					moveEnemies();
					checkCollions();
					addNewObject();

					gamePanel.repaint();
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();
	}
	
	/**
	 * uygulamamızın ekrana goruntu basacagı kısım.
	 */
	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
		isGamingRuning = true;
		startGame();

	}
	/**
	 * mouse olaylarımızı kontrol eden sınıf.
	 */
	private void addMouseEvents() {
		gamePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) { // fareden elini cektin
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) { // fare basılı
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		gamePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) { //
				xTarget = e.getX();
				yTarget = e.getY();

			}

			@Override
			public void mouseDragged(MouseEvent arg0) { // basılarak hareket
														// etmesi,klasor
														// tasıması gibi
				// TODO Auto-generated method stub

			}
		});
	}

}
