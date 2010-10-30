package dars.gui;
import javax.swing.*;
import javax.swing.border.Border;

import dars.OutputConsumer;
import dars.event.DARSEvent;
import dars.gui.*;
import java.awt.*;

public class GUI extends JFrame
implements OutputConsumer {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel simPanel = new JPanel();
  private JPanel logPanel = new JPanel();
  private JPanel nodeAttributesPanel = new JPanel();
  
  private LogArea logArea = new LogArea();
  private NodeAttributesArea nodeAttributesArea = new NodeAttributesArea();
  private SimArea simArea = new SimArea();
  
  public GUI() {
    this.pack();
    // Tell this JFrame to exit the program when this window closes
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);



    // Setup a new layout for the outermost part of the frame. We'll use the border layout.
    this.setLayout(new BorderLayout());
    
    // Allocate as follows:
    /*
         _________________
        |             |   |
        |             |   |
        |  CENTER     | <-|-- EAST
        |             |   |
        |             |   |
        |_____________|___|

    */
    // Add a center panel, this will serve us merely in a layout capacity.
    JPanel subpanel = new JPanel();
    this.add(subpanel, BorderLayout.CENTER);
    
    
    // Add the east panel.
    nodeAttributesPanel.setLayout(new BorderLayout());
    nodeAttributesPanel.add(nodeAttributesArea, BorderLayout.CENTER);
    this.add(nodeAttributesPanel, BorderLayout.EAST);
   
    /* Elaborate upon the layout of the subpanel. Do this:
         ______________
        |             |
        |             |
        |  CENTER     |
        |_____________|
        |             |
        |__SOUTH______|

   */
   //Use another borderlayout for the subpanel.
   subpanel.setLayout(new BorderLayout());

   //Add the GuiCanvas to the Center part
   simPanel.setLayout(new BorderLayout());
   simPanel.add(simArea, BorderLayout.CENTER);
   subpanel.add(simPanel, BorderLayout.CENTER);
   
   //Add the Status log panel to the bottom part.
   logPanel.setLayout(new BorderLayout());
   logPanel.add(logArea, BorderLayout.CENTER);
   subpanel.add(logPanel, BorderLayout.SOUTH); 
   
   
   //setup the borders
   setBorders();
   
   


   //setup the preferred sizes later
   SwingUtilities.invokeLater(new Runnable() {
     public void run() {
       setSizes();
     }
   });
   
   //Show everything
   this.setVisible(true);

   
  }

private void setSizes() {
  GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
  Rectangle r =graphicsEnvironment.getMaximumWindowBounds();
  setMaximizedBounds(r);
  Dimension windowSize = new Dimension(r.width, r.height);
  
  this.setPreferredSize(windowSize);
  logPanel.setPreferredSize(new Dimension((int)(windowSize.width*.8), (int)(windowSize.height*.2)));
  simPanel.setPreferredSize(new Dimension( (int)(windowSize.width*.8), (int)(windowSize.height*.8)));
  nodeAttributesPanel.setPreferredSize(new Dimension((int)(windowSize.width*.2), (int)(windowSize.height)));

  pack();
 
}

private void setBorders() {
  Border raisedBevel, loweredBevel, compound;
  raisedBevel = BorderFactory.createRaisedBevelBorder();
  loweredBevel = BorderFactory.createLoweredBevelBorder();
  compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
  simPanel.setBorder(compound);
  logPanel.setBorder(compound);
  nodeAttributesPanel.setBorder(compound);

}



public void consumeOutput(DARSEvent e) {
	// TODO Auto-generated method stub
	
}
  

};
