package Game;

import java.io.File;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gamePlay=new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("BRICKBREAKER GAME");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        obj.add(gamePlay);
        
	}

}
