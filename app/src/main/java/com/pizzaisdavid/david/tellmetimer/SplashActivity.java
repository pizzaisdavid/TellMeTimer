package com.pizzaisdavid.david.tellmetimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashActivity extends AppCompatActivity {
  private static final Logger logger = LoggerFactory.getLogger(SplashActivity.class);


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    logger.info("Initializing");
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }
}