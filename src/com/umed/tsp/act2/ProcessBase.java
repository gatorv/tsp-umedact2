package com.umed.tsp.act2;

import java.awt.Color;

import javax.swing.JProgressBar;

public class ProcessBase implements Runnable {
	private JProgressBar progress;
	private int sleepTime;
	private boolean finalized;

	public ProcessBase(JProgressBar p, int time) {
		progress = p;
		sleepTime = time;
		finalized = false;
	}

	@Override
	public void run() {
		progress.setForeground(Color.ORANGE);
		for (int i = 0; i <= 100; i++) {
			if (finalized == true) {
				finalized = false;
				break;
			}
			progress.setValue(i);
			System.out.println("Proceso " + Thread.currentThread().getName() + " ejecutandose");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		progress.setForeground(Color.GREEN);
		System.out.println("Proceso " + Thread.currentThread().getName() + " finalizado");
	}

	public JProgressBar getProgressBar() {
		return progress;
	}
	
	public void setFinalized(boolean f) {
		finalized = f;
	}
	
	public boolean isFinalized() {
		return finalized;
	}
}
