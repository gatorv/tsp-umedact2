package com.umed.tsp.act2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class ThreadSimulatorWindow {
	private JFrame simulatorFrame;
	private JProgressBar progressWord;
	private JProgressBar progressExcel;
	private JProgressBar progressPaint;
	private ProcessBase procesoWord;
	private ProcessBase procesoExcel;
	private ProcessBase procesoPaint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadSimulatorWindow window = new ThreadSimulatorWindow();
					window.simulatorFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThreadSimulatorWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		simulatorFrame = new JFrame();
		simulatorFrame.setTitle("Simulador de Procesos");
		simulatorFrame.setBounds(100, 100, 696, 206);
		simulatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		simulatorFrame.getContentPane().setLayout(null);
		
		JLabel lblProcessWord = new JLabel("Proceso Word");
		lblProcessWord.setBounds(32, 12, 129, 23);
		simulatorFrame.getContentPane().add(lblProcessWord);
		
		JLabel lblProcessExcel = new JLabel("Proceso Excel");
		lblProcessExcel.setBounds(32, 50, 129, 15);
		simulatorFrame.getContentPane().add(lblProcessExcel);
		
		JLabel lblProcessPaint = new JLabel("Proceso Paint");
		lblProcessPaint.setBounds(32, 85, 129, 15);
		simulatorFrame.getContentPane().add(lblProcessPaint);
		
		progressWord = new JProgressBar();
		progressWord.setBounds(159, 12, 501, 23);
		simulatorFrame.getContentPane().add(progressWord);
		
		progressExcel = new JProgressBar();
		progressExcel.setBounds(159, 47, 501, 23);
		simulatorFrame.getContentPane().add(progressExcel);
		
		progressPaint = new JProgressBar();
		progressPaint.setBounds(159, 83, 501, 23);
		simulatorFrame.getContentPane().add(progressPaint);
		
		JButton btnStart = new JButton("Iniciar");
		btnStart.setBounds(32, 123, 117, 25);
		simulatorFrame.getContentPane().add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				procesoWord = new ProcessWord(progressWord);
				procesoExcel = new ProcessExcel(progressExcel);
				procesoPaint = new ProcessPaint(progressPaint);
				
				Thread hiloWord = new Thread(procesoWord, "Word");
				hiloWord.start();
				
				Thread hiloExcel = new Thread(procesoExcel, "Excel");
				hiloExcel.start();
				
				Thread hiloPaint = new Thread(procesoPaint, "Paint");
				hiloPaint.start();
			}
		});
		
		JButton btnEnd = new JButton("Finalizar");
		btnEnd.setBounds(543, 123, 117, 25);
		simulatorFrame.getContentPane().add(btnEnd);
		btnEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				procesoWord.setFinalized(true);
				procesoExcel.setFinalized(true);
				procesoPaint.setFinalized(true);
			}
		});
	}
}
