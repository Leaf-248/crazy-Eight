/*
 * @author Gateway Instructors
 * @version 1.0
 */

import java.util.Scanner;

/**
 * This class represents a human player (user) in a game of Crazy Eights.
 */
public class User extends Player {

   /** Message to print when prompting User to enter Card number. */
   public static final String SELECT_CARD_FROM_HAND_PROMPT =
      "Select a card from your hand or enter 0 to draw a new card:  ";

   /** Message to print when user selects a Card that is not playable. */
   public static final String CARD_DOES_NOT_MATCH_MESSAGE =
      "\n--- This card is not a match!\n\n";
      
   /** Message to print when user attempts to draw a Card, but is already
       holding a playable Card in their hand. */
   public static final String NO_DRAW_IF_HAVE_PLAYABLE_CARD_MESSAGE =
      "\n--- Hand contains playable card, so you may not draw " +
      "from draw pile!\n\n";

   /** Scanner used to read user's input. */
   private Scanner input;

   /** 
    * Create a new human player with the specified name and an empty hand.
    * @param theName the name of the human player
    * @param in the Scanner to use to collect input from the user
    */
   public User(String theName, Scanner in) {
      super(theName);
      input = in;
   }


   /**
    * Allow the user to make a move (take one turn). If user's hand
    * contains a playable card, they must select a card from their hand 
    * and "play" it to end their move. If no card currently in the user's
    * hand is playable, then they must draw from the drawpile until a
    * playable card is drawn, and "play" that one. Makes use of named
    * constants above to match expected message formatting.
    * @param crazyEight gives access to the "draw pile"
    * @param top the top of the "discard pile"
    * @return the card played by the user
    */
   public Card makeMove(Game crazyEight, Card top) {
      // default prompt telling user to draw 
      System.out.print(SELECT_CARD_FROM_HAND_PROMPT);
      
      int index = input.nextInt();
      int fin = 0; 
      //check if hand contains a playable card
      //SELECT_CARD_FROM_HAND_PROMPT
      
      
      while (!this.hasPlayableCard(top)) {          
         
         if (index != 0) {
            while (index != 0) {
               System.out.print(SELECT_CARD_FROM_HAND_PROMPT);
               index = input.nextInt();
            }
                      
         } else {
            this.hand.addCard(crazyEight.draw());   
            System.out.println("*** " + this.getName() + " draws " + 
                               this.hand.top().toString());
            
            System.out.print(hand.toString());            
            System.out.print(SELECT_CARD_FROM_HAND_PROMPT);  
            index = input.nextInt();
         }
      }
   
      //while theres a playable card in hand
      
      
      while (this.hasPlayableCard(top)) {
         
         //this while loop makes sure that index has a valid card         
         if ((index <= 0 || index > this.hand.numFilled)) {
            while (index <= 0 || index > this.hand.numFilled) {
               if (index == 0) {
                  System.out.print(NO_DRAW_IF_HAVE_PLAYABLE_CARD_MESSAGE);
               }
               System.out.print(SELECT_CARD_FROM_HAND_PROMPT);
               index = input.nextInt();
            
            }
         } 
         if (index > 0 && index <= this.hand.numFilled && 
                crazyEight.cardMatches(top, this.hand.getCard(index - 1))) {
             
            return this.hand.discard(index - 1);  
         }
         //while the selected car and the top card doesn't match       
          
         else if (!crazyEight.cardMatches(top, hand.getCard(index - 1))) {
            System.out.print(CARD_DOES_NOT_MATCH_MESSAGE); 
            System.out.print(SELECT_CARD_FROM_HAND_PROMPT);
            index = input.nextInt();    
         }            
         
      }   
      return this.hand.discard(index - 1);
         
   }      
} 