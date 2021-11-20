package com.example.tttgameframework.tickettoride.players;

import android.graphics.Color;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tttgameframework.GameFramework.Game;
import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.tttgameframework.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;
import com.example.tttgameframework.GameFramework.utilities.Logger;
import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.TTRMainActivity;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;
import com.example.tttgameframework.tickettoride.views.TTRSurfaceView;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TTRHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, View.OnClickListener {
    /* instance variables */
    //these variables will reference widgets that will be modified during play
    private ImageButton blackTrainHandButton = null;
    private ImageButton orangeTrainHandButton = null;
    private ImageButton pinkTrainHandButton = null;
    private ImageButton whiteTrainHandButton = null;
    private ImageButton wildTrainHandButton = null;
    private Button confirmButton = null;
    private Button cancelButton = null;
    private ImageButton drawTicketsButton = null;
    private ImageButton faceup1Button = null;
    private ImageButton faceup2Button = null;
    private ImageButton faceup3Button = null;
    private ImageButton faceup4Button = null;
    private ImageButton faceup5Button = null;
    private ImageButton drawTrainButton = null;
    private EditText ticketList = null;
    private float Xratio;
    private float Yratio;
    private float Rratio;
    private int numCardsSelected;

    //variable that holds type of action (implement enum later)
    public enum ACTION {
        DRAW,
        PLACE,
        TICKET,
        NONE,
    }

    private ACTION typeAction;

    //this variable will hold the cards selected in button
    private ArrayList<Boolean> selected;

    //this variable will hold the actions wishes to be taken the turn off.
    private ArrayList<GameAction> turnActions;

    private GameMainActivity myActivity; //android activity that we are running.
    private TTRSurfaceView surfaceView;
    //holds the player's game state
    private TTRState state;

    //holds the number of wilds the player uses
    private int wilds;

    //holds the path that wants to be used
    private Path path;

    private ArrayList<Integer> selectedTickets;

    //an array list to hold all the face up buttons
    private ArrayList<ImageButton> faceUpButtons;

    //first turn varibale
    private int firstTurn;

    //holds the path number
    private int pathNumber;

    //holds whether the card deck is empty or not
//    private boolean emptyCardDeck;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public TTRHumanPlayer(String name) {
        super(name);
        wilds = 0;
        firstTurn = 1;
        path = null;
        selected = new ArrayList<Boolean>();
        selectedTickets = new ArrayList<Integer>();
        faceUpButtons = new ArrayList<ImageButton>();
        turnActions = new ArrayList<GameAction>();
        numCardsSelected = 0;
        Xratio = (float) (25.0/32.0);
        Yratio = (float) (2.0/3.0);
        Rratio = Yratio;
//        emptyCardDeck = false;

        for (int i = 0; i < 7; i++) {
            selected.add(false);
        }
        for(int i = 0; i < 2; i++) {
            selectedTickets.add(0);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Xratio = (float) (32.0/25.0);
        Yratio = (float) (3.0/2.0);
        Rratio = Yratio;

        //part grabbed from TTTHumanPlayer
        //ignore if not an "up" event
        if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
            return true;
        }

        if(firstTurn == 1){
            firstTurn = 0;
            game.sendAction(new DrawTickets(this, null));
            return true;
        }
        //get the x and y coordinates of the touch location
        //convert them to square coordinates
        int x = (int) (motionEvent.getX()*Xratio);
        int y = (int) (motionEvent.getY()*Yratio);
        //checks if the coordinates are valid coordinates
        if (x > 0 && x < 1800 && y > 0 && y < 1300) {
            ArrayList<Path> allPaths = state.getAllPaths();
            //if the coordinates are in the path picking range then
            //find the path chosen and assign it to variable
            if (x >= 360 && x <= 390 && y <= 265 && y >= 155) {
                //checks if the its the path 1: Astoria -> Tillamook white
                path = allPaths.get(18);
                pathNumber = 18;
                typeAction = ACTION.PLACE;
            } else if (x >= 395 && x <= 425 && y <= 265 && y >= 155) {
                //checks if its the path 2: Astoria - Tillamook Orange
                path = allPaths.get(0);
                pathNumber = 0;
                typeAction = ACTION.PLACE;

            } else if (x >= 426 && x <= 625 && y <= 230 && y >= 100) {
                //checks if its the path 3: Astoria - Portland grey
                path = allPaths.get(24);
                pathNumber = 24;
                typeAction = ACTION.PLACE;


            } else if (x >= 426 && x <= 575 && y <= 300 && y >= 270) {
                //checks if its the path 4: Tillamook - Portland pink
                path = allPaths.get(6);
                pathNumber = 6;
                typeAction = ACTION.PLACE;


            } else if (x >= 426 && x <= 575 && y <= 330 && y >= 300) {
                //checks if its the path 5: Tillamook - Portland black
                path = allPaths.get(12);
                pathNumber = 12;
                typeAction = ACTION.PLACE;


            } else if (x >= 325 && x <= 365 && y <= 470 && y >= 325) {
                //checks if its the path 6: Tillamook - Newport grey left
                path = allPaths.get(30);
                pathNumber = 30;
                typeAction = ACTION.PLACE;


            } else if (x >= 366 && x <= 410 && y <= 470 && y >= 325) {
                //checks if its the path 7: Tillamook - Newport grey right
                path = allPaths.get(31);
                pathNumber = 31;
                typeAction = ACTION.PLACE;


            } else if (x >= 690 && x <= 975 && y <= 255 && y >= 225) {
                //checks if its the path 8: Portland - The Dalles orange
                path = allPaths.get(1);
                pathNumber = 1;
                typeAction = ACTION.PLACE;

            } else if (x >= 690 && x <= 975 && y <= 305 && y >= 256) {
                //checks if its the path 7: Portland - The Dalles pink
                path = allPaths.get(7);
                pathNumber = 7;
                typeAction = ACTION.PLACE;

            } else if (x >= 590 && x <= 625 && y <= 405 && y >= 310) {
                //checks if its the path 26: Portland - Salem grey left
                path = allPaths.get(26);
                pathNumber = 26;
                typeAction = ACTION.PLACE;

            } else if (x >= 626 && x <= 660 && y <= 405 && y >= 310) {
                //checks if its the path 27: Portland- Salem grey right
                path = allPaths.get(27);
                pathNumber = 27;
                typeAction = ACTION.PLACE;

            } else if (x >= 1050 && x <= 1430 && y <= 255 && y >= 225) {
                //checks if its the path 19: The Dalles - Pendleton white
                path = allPaths.get(19);
                pathNumber = 19;
                typeAction = ACTION.PLACE;

            } else if (x >= 1050 && x <= 1430 && y <= 305 && y >= 256) {
                //checks if its the path 13: The Dalles - Pendleton black
                path = allPaths.get(13);
                pathNumber = 13;
                typeAction = ACTION.PLACE;

            } else if (x >= 965 && x <= 995 && y <= 645 && y >= 305) {
                //checks if its the path 28: The Dalles - Bend grey left
                path = allPaths.get(28);
                pathNumber = 28;
                typeAction = ACTION.PLACE;

            } else if (x >= 1000 && x <= 1030 && y <= 645 && y >= 305) {
                //checks if its the path 29: The Dalles - Bend grey right
                path = allPaths.get(29);
                pathNumber = 29;
                typeAction = ACTION.PLACE;

            } else if (x >= 1050 && x <= 1550 && y <= 675 && y >= 315) {
                //checks if its the path 10: Pendleton - Bend pink
                path = allPaths.get(10);
                pathNumber = 10;
                typeAction = ACTION.PLACE;

            } else if (x >= 1590 && x <= 1700 && y <= 310 && y >= 250) {
                //checks if its the path 14: Pendleton - La Grand black
                path = allPaths.get(14);
                pathNumber = 14;
                typeAction = ACTION.PLACE;

            } else if (x >= 1590 && x <= 1700 && y <= 375 && y >= 311) {
                //checks if its the path 3: Pendleton - La Grand orange
                path = allPaths.get(3);
                pathNumber = 3;
                typeAction = ACTION.PLACE;

            } else if (x >= 400 && x <= 570 && y <= 510 && y >= 410) {
                //checks if its the path 37: Newport - Salem grey
                path = allPaths.get(37);
                pathNumber = 37;
                typeAction = ACTION.PLACE;

            } else if (x >= 300 && x <= 390 && y <= 850 && y >= 525) {
                //checks if its the path 8: Newport - Coosbay pink
                path = allPaths.get(8);
                pathNumber = 8;
                typeAction = ACTION.PLACE;

            } else if (x >= 391 && x <= 550 && y <= 650 && y >= 511) {
                //checks if its the path 21: Newport - Eugene white
                path = allPaths.get(21);
                pathNumber = 21;
                typeAction = ACTION.PLACE;

            } else if (x >= 551 && x <= 581 && y <= 650 && y >= 450) {
                //checks if its the path 15: Salem - Eugene black
                path = allPaths.get(15);
                pathNumber = 15;
                typeAction = ACTION.PLACE;

            } else if (x >= 582 && x <= 625 && y <= 650 && y >= 450) {
                //checks if its the path 2: Salem - Eugene orange
                path = allPaths.get(2);
                pathNumber = 2;
                typeAction = ACTION.PLACE;

            } else if (x >= 626 && x <= 964 && y <= 640 && y >= 425) {
                //checks if its the path 20: Salem - Bend white
                path = allPaths.get(20);
                pathNumber = 20;
                typeAction = ACTION.PLACE;

            } else if (x >= 1551 && x <= 1750 && y <= 800 && y >= 400) {
                //checks if its the path 4: La Grand - Burns orange
                path = allPaths.get(4);
                pathNumber = 4;
                typeAction = ACTION.PLACE;

            } else if (x >= 600 && x <= 950 && y <= 700 && y >= 641) {
                //checks if its the path 9: Eugene - Bend pink
                path = allPaths.get(9);
                pathNumber = 9;
                typeAction = ACTION.PLACE;

            } else if (x >= 500 && x <= 550 && y <= 900 && y >= 700) {
                //checks if its the path 32: Eugene - Roseburg left
                path = allPaths.get(32);
                pathNumber = 32;
                typeAction = ACTION.PLACE;

            } else if (x >= 551 && x <= 599 && y <= 900 && y >= 700) {
                //checks if its the path 33: Eugene = Roseburg grey right
                path = allPaths.get(33);
                pathNumber = 33;
                typeAction = ACTION.PLACE;

            } else if (x >= 875 && x <= 1010 && y <= 1175 && y >= 701) {
                //checks if its the path 5: Bend - KFalls orange
                path = allPaths.get(38);
                pathNumber = 38;
                typeAction = ACTION.PLACE;

            } else if (x >= 1011 && x <= 1450 && y <= 825 && y >= 676) {
                //checks if its the path 36: Bend - Burns grey
                path = allPaths.get(36);
                pathNumber = 36;
                typeAction = ACTION.PLACE;

            } else if (x >= 1250 && x <= 1500 && y <= 1200 && y >= 826) {
                //checks if its the path 11: Burns - Lake view pink
                path = allPaths.get(11);
                pathNumber = 11;
                typeAction = ACTION.PLACE;

            } else if (x >= 350 && x <= 499 && y <= 905 && y >= 851) {
                //checks if its the path 22: Coosbay - Roseburg white
                path = allPaths.get(22);
                pathNumber = 22;
                typeAction = ACTION.PLACE;

            } else if (x >= 325 && x <= 500 && y <= 1100 && y >= 906) {
                //checks if its the path 17: Coosbay - Grants Pass black
                path = allPaths.get(17);
                pathNumber = 17;
                typeAction = ACTION.PLACE;

            } else if (x >= 501 && x <= 550 && y <= 1100 && y >= 950) {
                //checks if its the path 34: Roseburg - Grants Pass grey
                path = allPaths.get(34);
                pathNumber = 34;
                typeAction = ACTION.PLACE;

            } else if (x >= 551 && x <= 874 && y <= 1125 && y >= 900) {
                //checks if its the path 35: Roseburg - KFalls grey
                path = allPaths.get(35);
                pathNumber = 35;
                typeAction = ACTION.PLACE;

            } else if (x >= 551 && x <= 850 && y <= 1200 && y >= 1126) {
                //checks if its the path 5: Grants Pass - KFalls orange
                path = allPaths.get(5);
                pathNumber = 5;
                typeAction = ACTION.PLACE;

            } else if (x >= 900 && x <= 1200 && y <= 1225 && y >= 1176) {
                //checks if its the path 23: KFalls - LakeView white
                path = allPaths.get(23);
                pathNumber = 23;
                typeAction = ACTION.PLACE;

            }
        } else if (x >= 1700 && x <= 2100 && y >= 900 && y <= 1200) {
            //if the coordinates are in the ticket choosing range then
            //find which ticket is chosen and make it true in the arraylist
            if (y <= 1050) {
                //player chooses top ticket
                if (selectedTickets.get(0) == 0) {
                    selectedTickets.set(0, 1);
                } else {
                    selectedTickets.set(0, 0);
                }
                typeAction = ACTION.TICKET;
            } else {
                //player chooses bottom ticket
                if (selectedTickets.get(1) == 0) {
                    selectedTickets.set(1, 1);
                } else {
                    selectedTickets.set(1, 0);
                }
                typeAction = ACTION.TICKET;
            }

        } else {
            //not a valid coordinate so it will flash screen
            flash(Color.RED, 20, Color.BLACK);
            return true;
        }

        surfaceView.setState(state, selected, selectedTickets, path);
        surfaceView.invalidate();
        return true;
    }

    //this is where we will send actions to the game
    @Override
    public View getTopView() {
        //give id of our xml layout for G
        return myActivity.findViewById(R.id.mainView2);
    }

    //impolement as TTT
    @Override
    public void receiveInfo(GameInfo info) {
        //if game info is gamestate then use gamestate to update what is being drawn

        if (surfaceView == null) {
            return;
        }
        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo){
            if(info instanceof IllegalMoveInfo){
                System.out.println("Illegal Move");
                flash(Color.RED, 20,Color.BLACK);
            }
            System.out.println("Not your turn move");
            flash(Color.BLACK, 20,Color.BLACK);
            return;
        }

        else if (!(info instanceof TTRState)) {
            System.out.println("Bad move");
            flash(Color.RED, 20,Color.BLACK);
            return;
        }
        else {
            System.out.println("State move");
            state = new TTRState((TTRState) info);
            surfaceView.setText(ticketList);
            surfaceView.setState(state, selected, selectedTickets, path);
            System.out.println("Updating info");
            surfaceView.invalidate();
            ArrayList<TTRState.CARD> faceUps = state.getFaceUp();
            for(int i = 0; i < faceUps.size(); i++) {
                //displays the current cards in the face up section
                ImageButton button = faceUpButtons.get(i);
                if(faceUps.get(i) == TTRState.CARD.BLACKCARD){
                    button.setImageResource(R.drawable.black_train_h);

                } else if(faceUps.get(i) == TTRState.CARD.ORANGECARD){
                    button.setImageResource(R.drawable.orange_train_h);

                } else if(faceUps.get(i) == TTRState.CARD.PINKCARD){
                    button.setImageResource(R.drawable.purple_train_h);

                } else if(faceUps.get(i) == TTRState.CARD.WHITECARD){
                    button.setImageResource(R.drawable.white_train_h);

                } else if(faceUps.get(i) == TTRState.CARD.WILDCARD){
                    button.setImageResource(R.drawable.wild_train_h);

                }else{
                    button.setImageResource(R.color.purple_500);
                }
                faceUpButtons.set(i, button);
            }
            //draw the random card
            if(state.getCardDeck().size() == 0){
                drawTrainButton.setImageResource(R.color.purple_500);
            }else{
                drawTrainButton.setImageResource(R.drawable.train_draw);
            }
            blackTrainHandButton.setImageResource(R.drawable.black_train_v);
            whiteTrainHandButton.setImageResource(R.drawable.white_train_v);
            orangeTrainHandButton.setImageResource(R.drawable.orange_train_v);
            wildTrainHandButton.setImageResource(R.drawable.wild_train_v);
            pinkTrainHandButton.setImageResource(R.drawable.purple_train_v);
            //drawTrainButton.setImageResource(R.drawable.train_draw);
            drawTicketsButton.setImageResource(R.drawable.ticket_draw);

            Logger.log("TAG", "receiving");
        }


    }


    @Override
    public void setAsGui(GameMainActivity activity) {
        //set the myActivity to remember activity
        myActivity = activity;

        //set the layout as the layout resource
        activity.setContentView(R.layout.activity_main);

        //init the widget reference member variables
        this.blackTrainHandButton = (ImageButton) activity.findViewById(R.id.blackTrainHand);
        this.orangeTrainHandButton = (ImageButton) activity.findViewById(R.id.orangeTrainHand);
        this.pinkTrainHandButton = (ImageButton) activity.findViewById(R.id.pinkTrainHand);
        this.whiteTrainHandButton = (ImageButton) activity.findViewById(R.id.whiteTrainHand);
        this.wildTrainHandButton = (ImageButton) activity.findViewById(R.id.wildTrainHand);
        this.confirmButton = (Button) activity.findViewById(R.id.ConfirmButton);
        this.cancelButton = (Button) activity.findViewById(R.id.CancelButton);
        this.drawTicketsButton = (ImageButton) activity.findViewById(R.id.DrawTicketButton);
        this.faceup1Button = (ImageButton) activity.findViewById(R.id.FaceUp1Button);
        this.faceup2Button = (ImageButton) activity.findViewById(R.id.FaceUp2Button);
        this.faceup3Button = (ImageButton) activity.findViewById(R.id.FaceUp3Button);
        this.faceup4Button = (ImageButton) activity.findViewById(R.id.FaceUp4Button);
        this.faceup5Button = (ImageButton) activity.findViewById(R.id.FaceUp5Button);
        this.drawTrainButton = (ImageButton) activity.findViewById(R.id.DrawTrainButton);
        this.ticketList = (EditText) activity.findViewById(R.id.TicketList);

        //sets background of ticketList
        this.ticketList.setBackgroundColor(Color.parseColor("#F6D078"));
        //give the face up buttons to the instance array list
        faceUpButtons.add(this.faceup1Button);
        faceUpButtons.add(this.faceup2Button);
        faceUpButtons.add(this.faceup3Button);
        faceUpButtons.add(this.faceup4Button);
        faceUpButtons.add(this.faceup5Button);

        //set listeners for button presses
        blackTrainHandButton.setOnClickListener(this);
        orangeTrainHandButton.setOnClickListener(this);
        pinkTrainHandButton.setOnClickListener(this);
        whiteTrainHandButton.setOnClickListener(this);
        wildTrainHandButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        drawTicketsButton.setOnClickListener(this);
        faceup1Button.setOnClickListener(this);
        faceup2Button.setOnClickListener(this);
        faceup3Button.setOnClickListener(this);
        faceup4Button.setOnClickListener(this);
        faceup5Button.setOnClickListener(this);
        drawTrainButton.setOnClickListener(this);

        //set the sufaceView instance variable
        surfaceView = (TTRSurfaceView) myActivity.findViewById(R.id.mainView2);
        Logger.log("set listener", "OnTouch");
        surfaceView.setOnTouchListener(this);

    }

    @Override
    public void onClick(View button) {
        if(firstTurn == 1){
            firstTurn = 0;
            game.sendAction(new DrawTickets(this, null));
            return;
        }
        //if the player clicks draw train then send the action draw train
        if (button.getId() == R.id.DrawTrainButton) {
            //asks if the user already has chosen 2 actions already
            if (typeAction == ACTION.NONE || typeAction == ACTION.DRAW) {
                typeAction = ACTION.DRAW;
                if (numCardsSelected < 2) {
                    if (!selected.get(0)) {
                        selected.set(0, true);
                        numCardsSelected++;
                    } else if (selected.get(0) && !selected.get(1)) {
                        selected.set(1, true);
                        numCardsSelected++;
                    }
                }else if(selected.get(1)){
                    selected.set(0, false);
                    selected.set(1, false);
                    numCardsSelected -= 2;

                }else {
                    flash(Color.RED, 20,Color.BLACK);
                }
                surfaceView.setSelectedView(selected);
                surfaceView.invalidate();
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }

            //else if the player clicks on draw ticket then send the action draw ticket
        } else if (button.getId() == R.id.DrawTicketButton) {
            if (typeAction == ACTION.NONE && state.getTicketDeck().size() > 1) {
                typeAction = ACTION.TICKET;
                game.sendAction(new DrawTickets(this, null));
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }


            //else if the player clicks on a face up card
        } else if (button.getId() == R.id.FaceUp1Button) {
            if ((typeAction == ACTION.NONE || typeAction == ACTION.DRAW)) {
                typeAction = ACTION.DRAW;
                //checks for the 1 case that there are 2 cards selected and neither is this card
                if (!(numCardsSelected == 2 && !selected.get(2))) {
                    //flips the selection
                    selected.set(2, !selected.get(2));
                    //updates number of cards selected
                    if (selected.get(2)) {
                        numCardsSelected++;
                    } else {
                        numCardsSelected--;
                    }
                    //updates surface view
                    surfaceView.setSelectedView(selected);
                    surfaceView.invalidate();
                } else {
                    flash(Color.RED, 20,Color.BLACK);
                }
            }
        } else if (button.getId() == R.id.FaceUp2Button) {
                if ((typeAction == ACTION.NONE || typeAction == ACTION.DRAW)) {
                    typeAction = ACTION.DRAW;
                    //checks for the 1 case that there are 2 cards selected and neither is this card
                    if (!(numCardsSelected == 2 && !selected.get(3))) {
                        //flips the selection
                        selected.set(3, !selected.get(3));
                        //updates number of cards selected
                        if (selected.get(3)) {
                            numCardsSelected++;
                        } else {
                            numCardsSelected--;
                        }
                        //updates surface view
                        surfaceView.setSelectedView(selected);
                        surfaceView.invalidate();
                    } else {
                        flash(Color.RED, 20,Color.BLACK);
                    }
                }
        } else if (button.getId() == R.id.FaceUp3Button) {
            if ((typeAction == ACTION.NONE || typeAction == ACTION.DRAW)) {
                typeAction = ACTION.DRAW;
                //checks for the 1 case that there are 2 cards selected and neither is this card
                if (!(numCardsSelected == 2 && !selected.get(4))) {
                    //flips the selection
                    selected.set(4, !selected.get(4));
                    //updates number of cards selected
                    if (selected.get(4)) {
                        numCardsSelected++;
                    } else {
                        numCardsSelected--;
                    }
                    //updates surface view
                    surfaceView.setSelectedView(selected);
                    surfaceView.invalidate();
                } else {
                    flash(Color.RED, 20,Color.BLACK);
                }
            }
        } else if (button.getId() == R.id.FaceUp4Button) {
            if ((typeAction == ACTION.NONE || typeAction == ACTION.DRAW)) {
                typeAction = ACTION.DRAW;
                //checks for the 1 case that there are 2 cards selected and neither is this card
                if (!(numCardsSelected == 2 && !selected.get(5))) {
                    //flips the selection
                    selected.set(5, !selected.get(5));
                    //updates number of cards selected
                    if (selected.get(5)) {
                        numCardsSelected++;
                    } else {
                        numCardsSelected--;
                    }
                    //updates surface view
                    surfaceView.setSelectedView(selected);
                    surfaceView.invalidate();
                } else {
                    flash(Color.RED, 20,Color.BLACK);
                }
            }
        } else if (button.getId() == R.id.FaceUp5Button) {
            if ((typeAction == ACTION.NONE || typeAction == ACTION.DRAW)) {
                typeAction = ACTION.DRAW;
                //checks for the 1 case that there are 2 cards selected and neither is this card
                if (!(numCardsSelected == 2 && !selected.get(6))) {
                    //flips the selection
                    selected.set(6, !selected.get(6));
                    //updates number of cards selected
                    if (selected.get(6)) {
                        numCardsSelected++;
                    } else {
                        numCardsSelected--;
                    }
                    //updates surface view
                    surfaceView.setSelectedView(selected);
                    surfaceView.invalidate();
                } else {
                    flash(Color.RED, 20,Color.BLACK);
                }
            }
        } else if (button.getId() == R.id.ConfirmButton) {
            if (typeAction == ACTION.DRAW) {
                System.out.println("Draw");
                turnActions.add(new DrawTrains(this, selected));
                for (int i = 0; i < turnActions.size(); i++) {
                    game.sendAction(turnActions.get(i));
                }
                turnActions.clear();
                typeAction = ACTION.NONE;
                wilds = 0;
                path = null;
                for (int i = 0; i < 7; i++) {
                    selected.set(i, false);
                }
                for(int i = 0; i < 2; i++){
                    selectedTickets.set(i, 0);
                }
                numCardsSelected=0;
                surfaceView.invalidate();
            } else if (typeAction == ACTION.PLACE) {
                System.out.println("Place");
                if (wilds > 0 && turnActions.size() == 0) {
                    game.sendAction(new PlaceTrains(this, path, wilds, null, pathNumber));
                    turnActions.clear();
                    typeAction = ACTION.NONE;
                    wilds = 0;
                    path = null;
                    for (int i = 0; i < 7; i++) {
                        selected.set(i, false);
                    }
                    for(int i = 0; i < 2; i++){
                        selectedTickets.set(i, 0);
                    }
                    surfaceView.invalidate();
                } else if (turnActions.size() > 0) {
                    game.sendAction(turnActions.get(0));
                    turnActions.clear();
                    typeAction = ACTION.NONE;
                    wilds = 0;
                    path = null;
                    for (int i = 0; i < 7; i++) {
                        selected.set(i, false);
                    }
                    for(int i = 0; i < 2; i++){
                        selectedTickets.set(i, 0);
                    }
                    surfaceView.resetSelectedCardColor();
                    surfaceView.invalidate();
                } else {
                    flash(Color.RED, 20,Color.BLACK);
                }


            } else if (typeAction == ACTION.TICKET) {
                System.out.println("Ticket");
                //if the player is taking a draw turn then
                game.sendAction(new DrawTickets(this, selectedTickets));
                turnActions.clear();
                typeAction = ACTION.NONE;
                wilds = 0;
                path = null;
                for (int i = 0; i < 7; i++) {
                    selected.set(i, false);
                }
                for(int i = 0; i < 2; i++){
                    selectedTickets.set(i, 0);
                }
                surfaceView.invalidate();
            } else {
                //if the player did enter a action
                System.out.println("ANGRY");
                flash(Color.RED, 20,Color.BLACK);
            }

        } else if (button.getId() == R.id.CancelButton) {
            //if cancel is clicked then it will send
            turnActions.clear();
            typeAction = ACTION.NONE;
            wilds = 0;
            if(path == null){
                System.out.println("path is null");
            }
            path = null;
            for (int i = 0; i < 7; i++) {
                selected.set(i, false);
            }
            for(int i = 0; i < 2; i++){
                selectedTickets.set(i, 0);
            }
            numCardsSelected=0;
            surfaceView.resetSelectedCardColor();
            surfaceView.invalidate();

        } else if (button.getId() == R.id.whiteTrainHand) {
            if (typeAction == ACTION.PLACE) {
                System.out.println("Works?");
                if(turnActions.size() > 0) {
                    turnActions.remove(0);
                }
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.WHITECARD , pathNumber));
                surfaceView.setSelectedCardColor(TTRState.CARD.WHITECARD);
                surfaceView.invalidate();
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }
        } else if (button.getId() == R.id.blackTrainHand) {
            if (typeAction == ACTION.PLACE) {
                System.out.println("Works?");
                if(turnActions.size() > 0) {
                    turnActions.remove(0);
                }
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.BLACKCARD, pathNumber));
                surfaceView.setSelectedCardColor(TTRState.CARD.BLACKCARD);
                surfaceView.invalidate();
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }
        } else if (button.getId() == R.id.pinkTrainHand) {
            if (typeAction == ACTION.PLACE) {
                System.out.println("Works?");
                if(turnActions.size() > 0) {
                    turnActions.remove(0);
                }
                turnActions.add(new PlaceTrains(this, path, wilds, TTRState.CARD.PINKCARD, pathNumber));
                surfaceView.setSelectedCardColor(TTRState.CARD.PINKCARD);
                surfaceView.invalidate();
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }
        } else if (button.getId() == R.id.orangeTrainHand) {
            if (typeAction == ACTION.PLACE) {
                System.out.println("Works?");
                if(turnActions.size() > 0) {
                    turnActions.remove(0);
                }
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.ORANGECARD, pathNumber));
                surfaceView.setSelectedCardColor(TTRState.CARD.ORANGECARD);
                surfaceView.invalidate();
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }
        } else if (button.getId() == R.id.wildTrainHand) {
            if (typeAction == ACTION.PLACE) {
                System.out.println("Works?");
                wilds++;
            } else {
                flash(Color.RED, 20,Color.BLACK);
            }
        }

    }

//    public void setEmptyCardDeck(boolean bool){
//        emptyCardDeck = bool;
//    }
}
