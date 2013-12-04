package com.ctx.log.parser.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;


public class ToolBar extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	static private JButton runButton;
	static private JButton displayButton;
	private AnalysisListener analysisListener;
	private EventListenerList listenerList = new EventListenerList();
	
	
	public ToolBar(){
		
		setBorder(BorderFactory.createEtchedBorder());	
		runButton = new JButton("Start analysis");
		displayButton = new JButton("SIP Flow");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(runButton);
		add(displayButton);
		
		runButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				initAnalysisEvent(new AnalysisFormEvent("PROCESS_LOG_FILE"));
			}
		});
		
		displayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				initAnalysisEvent(new AnalysisFormEvent("START_DISPLAY_CALLS"));
			}
		});
		
	}

	
	public void setAnalysisListener(AnalysisListener analysisListener) {
		this.analysisListener = analysisListener;
	}
	
	public void initAnalysisEvent(AnalysisFormEvent event) {
		if(analysisListener != null) {
			analysisListener.sendEventLogAnalysis(event);
		}
	}
	
	
	public void SendParseEvent(ParseEvent event) {
		
		Object[] listeners = listenerList.getListenerList();
		for (int i=0; i< listeners.length;i+=2) {
			if(listeners[i] == ParseListener.class) {
				((ParseListener)listeners[i+1]).ParseEventOccurred(event);
			}
		}
	}

	public void addParseListener(ParseListener listener) {
		listenerList.add(ParseListener.class, listener);
	}

	public void deleteParseListener(ParseListener listener) {
		listenerList.remove(ParseListener.class, listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
