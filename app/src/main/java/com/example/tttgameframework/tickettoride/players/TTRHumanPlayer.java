package com.example.tttgameframework.tickettoride.players;

import android.graphics.Color;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tttgameframework.GameFramework.Game;
import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
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
    private float Xratio;
    private float Yratio;
    private float Rratio;

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
        Xratio = (float) (25.0/32.0);
        Yratio = (float) (2.0/3.0);
        Rratio = Yratio;

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
            game.sendAction(new DrawTickets(this, new ArrayList<Integer>()));
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

            } else if (x >= 395 && x <= 425 && y <= 265 && y >= 155) {
                //checks if its the path 2: Astoria - Tillamook Orange
                path = allPaths.get(0);


            } else if (x >= 426 && x <= 625 && y <= 230 && y >= 100) {
                //checks if its the path 3: Astoria - Portland grey
                path = allPaths.get(24);


            } else if (x >= 426 && x <= 575 && y <= 300 && y >= 270) {
                //checks if its the path 4: Tillamook - Portland pink
                path = allPaths.get(6);


            } else if (x >= 426 && x <= 575 && y <= 330 && y >= 300) {
                //checks if its the path 5: Tillamook - Portland black
                path = allPaths.get(12);


            } else if (x >= 325 && x <= 365 && y <= 470 && y >= 325) {
                //checks if its the path 6: Tillamook - Newport grey left
                path = allPaths.get(30);


            } else if (x >= 366 && x <= 410 && y <= 470 && y >= 325) {
                //checks if its the path 7: Tillamook - Newport grey right
                path = allPaths.get(31);


            } else if (x >= 690 && x <= 975 && y <= 255 && y >= 225) {
                //checks if its the path 8: Portland - The Dalles orange
                path = allPaths.get(1);

            } else if (x >= 690 && x <= 975 && y <= 305 && y >= 256) {
                //checks if its the path 7: Portland - The Dalles pink
                path = allPaths.get(7);

            } else if (x >= 590 && x <= 625 && y <= 405 && y >= 310) {
                //checks if its the path 26: Portland - Salem grey left
                path = allPaths.get(26);

            } else if(x >= 626 && x <= 660 && y <= 405  && y >= 310) {
                //checks if its the path 27: Portland- Salem grey right
                path = allPaths.get(27);

            }else if(x >= 1050 && x <= 1430 && y <= 255  && y >= 225) {
                //checks if its the path 19: The Dalles - Pendleton white
                path = allPaths.get(19);

            } else if(x >= 1050 && x <= 1430 && y <= 305  && y >= 256) {
                //checks if its the path 13: The Dalles - Pendleton black
                path = allPaths.get(13);

            } else if(x >= 965 && x <= 995 && y <= 645  && y >= 305) {
                //checks if its the path 28: The Dalles - Bend grey left
                path = allPaths.get(28);

            } else if(x >= 1000 && x <= 1030 && y <= 645  && y >= 305) {
                //checks if its the path 29: The Dalles - Bend grey right
                path = allPaths.get(29);

            } else if(x >= 1050 && x <= 1550 && y <= 675  && y >= 315) {
                //checks if its the path 10: Pendleton - Bend pink
                path = allPaths.get(10);

            } else if(x >= 1590 && x <= 1700 && y <= 310  && y >= 250) {
                //checks if its the path 14: Pendleton - La Grand black
                path = allPaths.get(14);

            } else if(x >= 1590 && x <= 1700 && y <= 375  && y >= 311) {
                //checks if its the path 3: Pendleton - La Grand orange
                path = allPaths.get(3);

            } else if(x >= 400 && x <= 570 && y <= 510  && y >= 410) {
                //checks if its the path 37: Newport - Salem grey
                path = allPaths.get(37);

            } else if(x >= 300 && x <= 390 && y <= 850 && y >= 525) {
                //checks if its the path 8: Newport - Coosbay pink
                path = allPaths.get(8);

            } else if(x >= 391 && x <= 550 && y <= 650  && y >= 511) {
                //checks if its the path 21: Newport - Eugene white
                path = allPaths.get(21);

            } else if(x >= 551 && x <= 581 && y <= 650  && y >= 450) {
                //checks if its the path 15: Salem - Eugene black
                path = allPaths.get(15);

            } else if(x >= 582 && x <= 625 && y <= 650  && y >= 450) {
                //checks if its the path 2: Salem - Eugene orange
                path = allPaths.get(2);

            } else if(x >= 626 && x <= 964 && y <= 640  && y >= 425) {
                //checks if its the path 20: Salem - Bend white
                path = allPaths.get(20);

            } else if(x >= 1551 && x <= 1750 && y <= 800  && y >= 400) {
                //checks if its the path 4: La Grand - Burns orange
                path = allPaths.get(4);

            } else if(x >= 600 && x <= 950 && y <= 700  && y >= 641) {
                //checks if its the path 9: Eugene - Bend pink
                path = allPaths.get(9);

            } else if(x >= 500 && x <= 550 && y <= 900  && y >= 700) {
                //checks if its the path 32: Eugene - Roseburg left
                path = allPaths.get(32);

            } else if(x >= 551 && x <= 599 && y <= 900  && y >= 700) {
                //checks if its the path 33: Eugene = Roseburg grey right
                path = allPaths.get(33);

            }else if(x >= 875 && x <= 1010 && y <= 1175  && y >= 701) {
                //checks if its the path 5: Bend - KFalls orange
                path = allPaths.get(38);

            } else if(x >= 1011 && x <= 1450 && y <= 825  && y >= 676) {
                //checks if its the path 36: Bend - Burns grey
                path = allPaths.get(36);

            } else if(x >= 1250 && x <= 1500 && y <= 1200  && y >= 826) {
                //checks if its the path 11: Burns - Lake view pink
                path = allPaths.get(11);

            } else if(x >= 350 && x <= 499 && y <= 905  && y >= 851) {
                //checks if its the path 22: Coosbay - Roseburg white
                path = allPaths.get(22);

            } else if(x >= 325 && x <= 500 && y <= 1100  && y >= 906) {
                //checks if its the path 17: Coosbay - Grants Pass black
                path = allPaths.get(17);

            } else if(x >= 501 && x <= 550 && y <= 1100  && y >= 950) {
                //checks if its the path 34: Roseburg - Grants Pass grey
                path = allPaths.get(34);

            } else if(x >= 551 && x <= 874 && y <= 1125  && y >= 900) {
                //checks if its the path 35: Roseburg - KFalls grey
                path = allPaths.get(35);

            } else if(x >= 551 && x <= 850 && y <= 1200  && y >= 1126) {
                //checks if its the path 5: Grants Pass - KFalls orange
                path = allPaths.get(5);

            } else if(x >= 900 && x <= 1200 && y <= 1225  && y >= 1176) {
                //checks if its the path 23: KFalls - LakeView white
                path = allPaths.get(23);

            }
        } else if (x >= 1700 && x <= 2100 && y >= 900 && y <= 1200) {
            //if the coordinates are in the ticket choosing range then
            //find which ticket is chosen and make it true in the arraylist
            if(y <= 1050){
                //player chooses top ticket
                selectedTickets.set(0, 1);
                System.out.println("Ticket selected");
            } else {
                //player chooses bottom ticket
                selectedTickets.set(1,1);

            }

        } else {
            //not a valid coordinate so it will flash screen
            flash(Color.RED, 20);
            return true;
        }
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
        } else if (!(info instanceof TTRState)) {
            flash(Color.RED, 20);
            return;
        } else {
            state = new TTRState((TTRState) info);
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

                } else if(faceUps.get(i) == TTRState.CARD.ORANGECARD){
                    button.setImageResource(R.drawable.orange_train_h);

                } else if(faceUps.get(i) == TTRState.CARD.WILDCARD){
                    button.setImageResource(R.drawable.wild_train_h);

                }
                faceUpButtons.set(i, button);
            }
            blackTrainHandButton.setImageResource(R.drawable.black_train_v);
            whiteTrainHandButton.setImageResource(R.drawable.white_train_v);
            orangeTrainHandButton.setImageResource(R.drawable.orange_train_v);
            wildTrainHandButton.setImageResource(R.drawable.wild_train_v);
            pinkTrainHandButton.setImageResource(R.drawable.purple_train_v);
            drawTrainButton.setImageResource(R.drawable.train_draw);
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
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //if hasn't chosen 2 actions then add another action
                    //sets the first or second card drawn to deck draw
                    if (selected.size() == 0) {
                        selected.set(0, true);
                    } else {
                        selected.set(1, true);
                    }
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }

            } else {
                flash(Color.RED, 20);
            }

            //else if the player clicks on draw ticket then send the action draw ticket
        } else if (button.getId() == R.id.DrawTicketButton) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.TICKET;
                game.sendAction(new DrawTickets(this, new ArrayList<Integer>()));
            } else {
                flash(Color.RED, 20);
            }


            //else if the player clicks on a face up card
        } else if (button.getId() == R.id.FaceUp1Button) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //sets the draw train to the 1st face up card
                    selected.set(2, true);
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }

            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.FaceUp2Button) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //sets the draw train to the 1st face up card
                    selected.set(3, true);
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }

            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.FaceUp3Button) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //sets the draw train to the 1st face up card
                    selected.set(4, true);
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }

            } else {
                flash(Color.RED, 20);
            }


        } else if (button.getId() == R.id.FaceUp4Button) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //sets the draw train to the 1st face up card
                    selected.set(5, true);
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.FaceUp5Button) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.DRAW;
                if (turnActions.size() < 2) {
                    //sets the draw train to the 1st face up card
                    selected.set(6, true);
                    turnActions.add(new DrawTrains(this, selected));
                } else {
                    flash(Color.RED, 20);
                }
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.ConfirmButton) {
            if (typeAction == ACTION.DRAW) {
                for (int i = 0; i < turnActions.size(); i++) {
                    game.sendAction(turnActions.get(i));
                }
            } else if (typeAction == ACTION.PLACE) {
                if (wilds > 0 && turnActions.size() == 0) {
                    game.sendAction(new PlaceTrains(this, path, wilds, null));
                } else if (turnActions.size() > 0) {
                    game.sendAction(new DrawTickets(this, selectedTickets));
                } else {
                    flash(Color.RED, 20);
                }


            } else if (typeAction == ACTION.TICKET) {
                //if the player is taking a draw turn then
                game.sendAction(turnActions.get(0));
            } else {
                //if the player did enter a action

                flash(Color.RED, 20);
            }

        } else if (button.getId() == R.id.CancelButton) {
            //if cancel is clicked then it will send
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


        } else if (button.getId() == R.id.whiteTrainHand) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.PLACE;
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.WHITECARD));
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.blackTrainHand) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.PLACE;
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.BLACKCARD));
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.pinkTrainHand) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.PLACE;
                turnActions.add(new PlaceTrains(this, path, wilds, TTRState.CARD.PINKCARD));
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.orangeTrainHand) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.PLACE;
                turnActions.add( new PlaceTrains(this, path, wilds, TTRState.CARD.ORANGECARD));
            } else {
                flash(Color.RED, 20);
            }
        } else if (button.getId() == R.id.wildTrainHand) {
            if (typeAction == ACTION.NONE) {
                typeAction = ACTION.PLACE;
                wilds++;
            } else {
                flash(Color.RED, 20);
            }
        }

    }
}
