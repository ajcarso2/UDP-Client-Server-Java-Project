package Assignment3Starter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 * The ClientGui class is a GUI frontend that displays an image grid, an input text box,
 * a button, and a text area for status. 
 * 
 * Methods of Interest
 * ----------------------
 * show(boolean modal) - Shows the GUI frame with the current state
 *     -> modal means that it opens the GUI and suspends background processes. Processing still happens 
 *        in the GUI. If it is desired to continue processing in the background, set modal to false.
 * newGame(int dimension) - Start a new game with a grid of dimension x dimension size
 * insertImage(String filename, int row, int col) - Inserts an image into the grid
 * appendOutput(String message) - Appends text to the output panel
 * submitClicked() - Button handler for the submit button in the output panel
 * 
 * Notes
 * -----------
 * > Does not show when created. show() must be called to show he GUI.
 * 
 */
public class ClientGui implements Assignment3Starter.OutputPanel.EventHandlers {
  JDialog frame;
  PicturePanel picturePanel;
  OutputPanel outputPanel;

  /**
   * Construct dialog
   */
  public ClientGui() {
    frame = new JDialog();
    frame.setLayout(new GridBagLayout());
    frame.setMinimumSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // setup the top picture frame
    picturePanel = new PicturePanel();
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 0.25;
    frame.add(picturePanel, c);

    // setup the input, button, and output area
    c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    c.weighty = 0.75;
    c.weightx = 1;
    c.fill = GridBagConstraints.BOTH;
    outputPanel = new OutputPanel();
    outputPanel.addEventHandlers(this);
    frame.add(outputPanel, c);
  }

  /**
   * Shows the current state in the GUI
   * @param makeModal - true to make a modal window, false disables modal behavior
   */
  public void show(boolean makeModal) {
    frame.pack();
    frame.setModal(makeModal);
    frame.setVisible(true);
  }

  /**
   * Creates a new game and set the size of the grid 
   * @param dimension - the size of the grid will be dimension x dimension
   */
  public void newGame(int dimension) {
    picturePanel.newGame(dimension);
    outputPanel.appendOutput("Started new game with a " + dimension + "x" + dimension + " board.");
  }

  /**
   * Insert an image into the grid at position (col, row)
   * 
   * @param filename - filename relative to the root directory
   * @param row - the row to insert into
   * @param col - the column to insert into
   * @return true if successful, false if an invalid coordinate was provided
   * @throws IOException An error occured with your image file
   */
  public boolean insertImage(String filename, int row, int col) throws IOException {
    String error = "";
    try {
      // insert the image
      if (picturePanel.insertImage(filename, row, col)) {
      // put status in output
        outputPanel.appendOutput("Inserting " + filename + " in position (" + row + ", " + col + ")");
        return true;
      }
      error = "File(\"" + filename + "\") not found.";
    } catch(PicturePanel.InvalidCoordinateException e) {
      // put error in output
      error = e.toString();
    }
    outputPanel.appendOutput(error);
    return false;
  }

  /**
   * Submit button handling
   * 
   * Change this to whatever you need
   */
  @Override
  public void submitClicked() {

    // An example how to update the points in the UI
    outputPanel.setPoints(10);

    // Pulls the input box text
    String input = outputPanel.getInputText();
    // if has input
    if (input.length() > 0) {
      // append input to the output panel
      outputPanel.appendOutput(input);
      // clear input text box
      outputPanel.setInputText("");
    }
  }
  
  /**
   * Key listener for the input text box
   * 
   * Change the behavior to whatever you need
   */
  @Override
  public void inputUpdated(String input) {
    if (input.equals("surprise")) {
      outputPanel.appendOutput("You found me!");
    }
  }

  public static void main(String[] args) throws IOException {
    // create the frame
    ClientGui main = new ClientGui();

    
    
    // setup the UI to display on image
    main.newGame(1);
    
    // add images to the grid
    main.insertImage("img/Jack_Sparrow/quote4.png", 0, 0);


  
    
    // show the GUI dialog as modal
    main.show(true); // you should not have your logic after this. You main logic should happen whenever "submit" is clicked
  }
}
