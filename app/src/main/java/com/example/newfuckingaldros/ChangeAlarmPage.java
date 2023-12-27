package com.example.newfuckingaldros;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 *  ChangeAlarmPage
 *  activity for the user to change his alarm sound
 */
public class ChangeAlarmPage extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    private MediaPlayer mediaPlayer;

    TextView ChangeAlarmPage;
    Button warning_ChangeAlarmPage,TheJigIsUp_ChangeAlarmPage,OD_ChangeAlarmPage,MoonlightSonata_ChangeAlarmPage,IcanDoWhatEverIWant_Finale_ChangeAlarmPage,HipToBeScared_ChangeAlarmPage;
    Button HailToTheKing_ChangeAlarmPage,FuneralDerangement_ChangeAlarmPage,Eyeless_ChangeAlarmPage,CriticalAcclaimStart_ChangeAlarmPage,CriticalAcclaimSolo_ChangeAlarmPage,AmericanPsychoLastScene_ChangeAlarmPage;
    Button MurderTrain_ChangeAlarmPage;
    ImageView warning_play_ChangeAlarmPage,TheJigIsUp_play_ChangeAlarmPage,OD_play_ChangeAlarmPage,MoonlightSonata_play_ChangeAlarmPage,IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage,HipToBeScared_play_ChangeAlarmPage;
    ImageView HailToTheKing_play_ChangeAlarmPage,FuneralDerangement_play_ChangeAlarmPage,Eyeless_play_ChangeAlarmPage,CriticalAcclaimStart_play_ChangeAlarmPage,CriticalAcclaimSolo_play_ChangeAlarmPage,AmericanPsychoLastScene_play_ChangeAlarmPage;
    ImageView MurderTrain_play_ChangeAlarmPage;
    ImageView warning_pause_ChangeAlarmPage,TheJigIsUp_pause_ChangeAlarmPage,OD_pause_ChangeAlarmPage,MoonlightSonata_pause_ChangeAlarmPage,IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage,HipToBeScared_pause_ChangeAlarmPage;
    ImageView HailToTheKing_pause_ChangeAlarmPage,FuneralDerangement_pause_ChangeAlarmPage,Eyeless_pause_ChangeAlarmPage,CriticalAcclaimStart_pause_ChangeAlarmPage,CriticalAcclaimSolo_pause_ChangeAlarmPage,AmericanPsychoLastScene_pause_ChangeAlarmPage;
    ImageView MurderTrain_pause_ChangeAlarmPage;
    ImageView Back_ChangeAlarmPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_alarm_page);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ChangeAlarmPage=(TextView)findViewById(R.id.ChangeAlarmPage);
        Back_ChangeAlarmPage = (ImageView) findViewById(R.id.Back_ChangeAlarmPage);

        warning_ChangeAlarmPage=(Button) findViewById(R.id.warning_ChangeAlarmPage);
        warning_play_ChangeAlarmPage=(ImageView) findViewById(R.id.warning_play_ChangeAlarmPage);
        warning_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.warning_pause_ChangeAlarmPage);

        MurderTrain_ChangeAlarmPage=(Button) findViewById(R.id.MurderTrain_ChangeAlarmPage);
        MurderTrain_play_ChangeAlarmPage=(ImageView) findViewById(R.id.MurderTrain_play_ChangeAlarmPage);
        MurderTrain_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.MurderTrain_pause_ChangeAlarmPage);

        TheJigIsUp_ChangeAlarmPage=(Button) findViewById(R.id.TheJigIsUp_ChangeAlarmPage);
        TheJigIsUp_play_ChangeAlarmPage=(ImageView) findViewById(R.id.TheJigIsUp_play_ChangeAlarmPage);
        TheJigIsUp_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.TheJigIsUp_pause_ChangeAlarmPage);

        OD_ChangeAlarmPage=(Button) findViewById(R.id.OD_ChangeAlarmPage);
        OD_play_ChangeAlarmPage=(ImageView) findViewById(R.id.OD_play_ChangeAlarmPage);
        OD_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.OD_pause_ChangeAlarmPage);

        MoonlightSonata_ChangeAlarmPage=(Button) findViewById(R.id.MoonlightSonata_ChangeAlarmPage);
        MoonlightSonata_play_ChangeAlarmPage=(ImageView) findViewById(R.id.MoonlightSonata_play_ChangeAlarmPage);
        MoonlightSonata_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.MoonlightSonata_pause_ChangeAlarmPage);

        IcanDoWhatEverIWant_Finale_ChangeAlarmPage=(Button) findViewById(R.id.IcanDoWhatEverIWant_Finale_ChangeAlarmPage);
        IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage=(ImageView) findViewById(R.id.IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage);
        IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage);

        HipToBeScared_ChangeAlarmPage=(Button) findViewById(R.id.HipToBeScared_ChangeAlarmPage);
        HipToBeScared_play_ChangeAlarmPage=(ImageView) findViewById(R.id.HipToBeScared_play_ChangeAlarmPage);
        HipToBeScared_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.HipToBeScared_pause_ChangeAlarmPage);

        HailToTheKing_ChangeAlarmPage=(Button) findViewById(R.id.HailToTheKing_ChangeAlarmPage);
        HailToTheKing_play_ChangeAlarmPage=(ImageView) findViewById(R.id.HailToTheKing_play_ChangeAlarmPage);
        HailToTheKing_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.HailToTheKing_pause_ChangeAlarmPage);

        FuneralDerangement_ChangeAlarmPage=(Button) findViewById(R.id.FuneralDerangement_ChangeAlarmPage);
        FuneralDerangement_play_ChangeAlarmPage=(ImageView) findViewById(R.id.FuneralDerangement_play_ChangeAlarmPage);
        FuneralDerangement_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.FuneralDerangement_pause_ChangeAlarmPage);

        Eyeless_ChangeAlarmPage=(Button) findViewById(R.id.Eyeless_ChangeAlarmPage);
        Eyeless_play_ChangeAlarmPage=(ImageView) findViewById(R.id.Eyeless_play_ChangeAlarmPage);
        Eyeless_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.Eyeless_pause_ChangeAlarmPage);

        CriticalAcclaimStart_ChangeAlarmPage=(Button) findViewById(R.id.CriticalAcclaimStart_ChangeAlarmPage);
        CriticalAcclaimStart_play_ChangeAlarmPage=(ImageView) findViewById(R.id.CriticalAcclaimStart_play_ChangeAlarmPage);
        CriticalAcclaimStart_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.CriticalAcclaimStart_pause_ChangeAlarmPage);

        CriticalAcclaimSolo_ChangeAlarmPage=(Button) findViewById(R.id.CriticalAcclaimSolo_ChangeAlarmPage);
        CriticalAcclaimSolo_play_ChangeAlarmPage=(ImageView) findViewById(R.id.CriticalAcclaimSolo_play_ChangeAlarmPage);
        CriticalAcclaimSolo_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.CriticalAcclaimSolo_pause_ChangeAlarmPage);

        AmericanPsychoLastScene_ChangeAlarmPage=(Button) findViewById(R.id.AmericanPsychoLastScene_ChangeAlarmPage);
        AmericanPsychoLastScene_play_ChangeAlarmPage=(ImageView) findViewById(R.id.AmericanPsychoLastScene_play_ChangeAlarmPage);
        AmericanPsychoLastScene_pause_ChangeAlarmPage=(ImageView) findViewById(R.id.AmericanPsychoLastScene_pause_ChangeAlarmPage);

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        warning_pause_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_pause_ChangeAlarmPage.setVisibility(View.GONE);OD_pause_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_pause_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_pause_ChangeAlarmPage.setVisibility(View.GONE);
        HailToTheKing_pause_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_pause_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_pause_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_pause_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_pause_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_pause_ChangeAlarmPage.setVisibility(View.GONE);
        MurderTrain_pause_ChangeAlarmPage.setVisibility(View.GONE);
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        warning_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("warning");
                Toast.makeText(getApplicationContext(),"Alarm changed to: warning", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        MurderTrain_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("MurderTrain");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Murder Train", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        TheJigIsUp_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("TheJigIsUp");
                Toast.makeText(getApplicationContext(),"Alarm changed to: The Jig Is Up", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        OD_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("OD");
                Toast.makeText(getApplicationContext(),"Alarm changed to: O.D ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        MoonlightSonata_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("MoonlightSonata");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Moonlight Sonata", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        IcanDoWhatEverIWant_Finale_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("IcanDoWhatEverIWant_Finale");
                Toast.makeText(getApplicationContext(),"Alarm changed to: I can Do What Ever I Want - Finale", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        HipToBeScared_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("HipToBeScared");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Hip To Be Scared", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        HailToTheKing_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("HailToTheKing");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Hail To The King", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        FuneralDerangement_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("FuneralDerangement");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Funeral Derangement", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        Eyeless_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("Eyeless");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Eyeless", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        CriticalAcclaimStart_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("CriticalAcclaimStart");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Critical Acclaim Start", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        CriticalAcclaimSolo_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("Critical Acclaim Solo");
                Toast.makeText(getApplicationContext(),"Alarm changed to: Critical Acclaim Solo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });
        AmericanPsychoLastScene_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalUser.getUser().setMyAlarm("AmericanPsychoLastScene");
                Toast.makeText(getApplicationContext(),"Alarm changed to: American Psycho Last Scene", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeAlarmPage.this, PopayePage.class);
                startActivity(intent);
                stopPlayer();
            }
        });





        warning_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                warning_play_ChangeAlarmPage.setVisibility(View.GONE);
                warning_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.warning);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();


            }
        });
        MurderTrain_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.murder_train);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        TheJigIsUp_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);
                TheJigIsUp_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.the__jig_is_up);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        OD_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                OD_play_ChangeAlarmPage.setVisibility(View.GONE);
                OD_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.od);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        MoonlightSonata_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);
                MoonlightSonata_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.moonlight_sonata);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);
                IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.i_can_do_whatever_i_want_finale);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        HipToBeScared_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HipToBeScared_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.hip_to_be_scared);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        HailToTheKing_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.hail_to_the_king);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        FuneralDerangement_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);
                FuneralDerangement_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.funeral_derangement);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        Eyeless_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);
                Eyeless_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.eyeless);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        CriticalAcclaimStart_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);
                CriticalAcclaimStart_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.critical_acclaim_start);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        CriticalAcclaimSolo_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);
                CriticalAcclaimSolo_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.critical_acclaim_solo);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });
        AmericanPsychoLastScene_play_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.GONE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.GONE);OD_play_ChangeAlarmPage.setVisibility(View.GONE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.GONE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.GONE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.GONE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.GONE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.GONE);Eyeless_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.GONE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.GONE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.GONE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.GONE);
                AmericanPsychoLastScene_pause_ChangeAlarmPage.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ChangeAlarmPage.this, R.raw.american_psycho_last_scene);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
                mediaPlayer.start();

            }
        });




        warning_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                warning_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        MurderTrain_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        TheJigIsUp_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                TheJigIsUp_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        OD_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                OD_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        MoonlightSonata_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MoonlightSonata_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                IcanDoWhatEverIWant_Finale_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        HipToBeScared_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HipToBeScared_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        HailToTheKing_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        FuneralDerangement_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                FuneralDerangement_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        Eyeless_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                Eyeless_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        CriticalAcclaimStart_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                CriticalAcclaimStart_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        CriticalAcclaimSolo_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                CriticalAcclaimSolo_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });
        AmericanPsychoLastScene_pause_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////////////////////////////////////////////
                warning_play_ChangeAlarmPage.setVisibility(View.VISIBLE);TheJigIsUp_play_ChangeAlarmPage.setVisibility(View.VISIBLE);OD_play_ChangeAlarmPage.setVisibility(View.VISIBLE);MoonlightSonata_play_ChangeAlarmPage.setVisibility(View.VISIBLE);IcanDoWhatEverIWant_Finale_play_ChangeAlarmPage.setVisibility(View.VISIBLE);HipToBeScared_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                HailToTheKing_play_ChangeAlarmPage.setVisibility(View.VISIBLE);FuneralDerangement_play_ChangeAlarmPage.setVisibility(View.VISIBLE);Eyeless_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimStart_play_ChangeAlarmPage.setVisibility(View.VISIBLE);CriticalAcclaimSolo_play_ChangeAlarmPage.setVisibility(View.VISIBLE);AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                MurderTrain_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                //////////////////////////////////////////////////////////////////////////////////////////////////////

                AmericanPsychoLastScene_play_ChangeAlarmPage.setVisibility(View.VISIBLE);
                AmericanPsychoLastScene_pause_ChangeAlarmPage.setVisibility(View.GONE);
                pause(view);

            }
        });



        Back_ChangeAlarmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    //end of onCreate()

    /**
     *  pause
     *  pauses the media player
     */
    public  void pause(View V) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer = null;
        }
    }
    public  void stop(View V) {
        stopPlayer();
    }

    /**
     *  stopPlayer
     *  stop the mediaPlayer
     */
    private  void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}