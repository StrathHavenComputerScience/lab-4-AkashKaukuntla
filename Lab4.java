public class Lab4
{
  public static void turnRight()
  {
    Robot.turnLeft();
    Robot.turnLeft();
    Robot.turnLeft();
  }
  //pre: The bot is facing any direction.
  //post: The bot is facing 90 degrees clockwise from where it was.
  public static void turnAround()
  {
    Robot.turnLeft();
    Robot.turnLeft();
  }
  //pre: The bot is facing any direction.
  //post: The bot is facing the opposite direction.
  public static void backUp()
  {
    turnAround();
    Robot.move();
    turnAround();
  }
  //pre: The bot is facing any direction.
  //post: The bot is facing the same direction one space to the back.
  public static void makeBar()
  {
     Robot.turnLeft();
     while(!Robot.onDark())
     {
         Robot.makeDark();
         if (Robot.frontIsClear())
        {
         Robot.move();
        }
     }    
  }
  //pre: The bot is facing right at the bottom of an unfilled bar.
  //post: The bot is facing up with the bar filled.
  public static void comeBack()
  {
      turnAround();
      while(Robot.frontIsClear())
      {
          Robot.move();
        }
      Robot.turnLeft();
      Robot.move();
  }
  //pre: The bot is at the end of a filled in bar.
  //post: The bot is facing left at the beginning of an unfilled bar.
  public static void completeBars()
  {
    while (Robot.frontIsClear())
    {
        makeBar();
        comeBack();
    }
    makeBar();
  }
  //pre: The bot is facing left at the beginning of an unfilled bar.
  //post: The bot is facing down in the bottom right square with all the bars filled.
  public static void testCompleteBars1()
  {
    Robot.load("bars1.txt");
    Robot.setDelay(0.025);
    completeBars();
  }
  
  public static void testCompleteBars2()
  {
    Robot.load("bars2.txt");
    Robot.setDelay(0.025);
    completeBars();
  }
  
  public static void goFront()
  {
    while (Robot.frontIsClear())
    {
        Robot.move();
    }
    turnAround();
    }
  //pre: The bot is facing up at the beginning of a row.
  //post: The bot is facing down at the end of the same row.
  public static void measureDark()
  {
    while (!Robot.onDark())
    {
        if (Robot.frontIsClear())
        {
            Robot.move();
        }
    }
    Robot.makeLight();
    }
  //pre: On the first row the bot is facing down at the end with a certain number of boxes darkened.
  //post: On the first row the bot is facing down with the end darkened box made light and the bot is on that square.
  public static void goToEnd()
  {
      while (Robot.frontIsClear())
    {
        Robot.move();
    }
    }
  //pre: The bot is on any square facing any direction.
  //post: The bot is at the end of that row facing the same direction.
  public static void goToNextRow()
  {
      Robot.turnLeft();
      Robot.move();
      Robot.turnLeft();
    }
  //pre: The bot is at the end of a row facing in.
  //post: The bot is at the end of the next row facing in.
  public static void addBox()
  {
      while (Robot.onDark())
      {
          Robot.move();
        }
      Robot.makeDark();
    }
  //pre: The bot is at the beginning of a row facing inward.
  //post: The bot is at the end of column of boxes with on an extra darkened square that it made.
  public static boolean isRowDark()
  {
      while (!Robot.onDark())
      {
          if (Robot.frontIsClear())
          {
              Robot.move();
            }
          else
          {
              turnAround();
              goFront();
              return false;
            }
        }
      turnAround();
      goFront();
      return true;
    }
  
  public static void combinePiles()
  {
    goFront();
    while (isRowDark())
    {
        measureDark();
        goToEnd();
        goToNextRow();
        addBox();
        goToEnd();
        goToNextRow();
    }
  }
  //pre: There are a given number of boxes in each row. The bot is facing up in the bottom left square.
  //post: All of the boxes are on the right row with the bot in the top left square facing down.
  public static void testCombinePiles1()
  {
    Robot.load("piles1.txt");
    Robot.setDelay(0.025);
    combinePiles();
  }
  
  public static void testCombinePiles2()
  {
    Robot.load("piles2.txt");
    Robot.setDelay(0.025);
    combinePiles();
  }
  
  public static boolean isFrontDark()
  {
     if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
      return false;
    }
      if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
        backUp();
        return false;
    }
    if (Robot.onDark())
    {
        backUp();
        backUp();
        return true;
    }
    else
    {
        backUp();
        backUp();
        return false;
    }
    }
    
  public static boolean isRightDark()
  {
     turnRight();
      if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
        Robot.turnLeft();
        return false;
    }
      if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
        backUp();
        Robot.turnLeft();
        return false;
    }
    if (Robot.onDark())
    {
        backUp();
        backUp();
        Robot.turnLeft();
        return true;
    }
    else
    {
        backUp();
        backUp();
        Robot.turnLeft();
        return false;
    }
    }
    
      public static boolean isLeftDark()
  {
     Robot.turnLeft();
      if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
        turnRight();
        return false;
    }
      if (Robot.frontIsClear())
     {
      Robot.move();
    }
    else
    {
        backUp();
        turnRight();
        return false;
    }
    if (Robot.onDark())
    {
        backUp();
        backUp();
        turnRight();
        return true;
    }
    else
    {
        backUp();
        backUp();
        turnRight();
        return false;
    }
    }
    
  public static void connectDots()
  {
    while(Robot.onDark())
    {
        if(isFrontDark())
        {
            Robot.move();
            Robot.makeDark();
            Robot.move();
        }
        else
        {
            if(isLeftDark())
            {
                Robot.turnLeft();
                Robot.move();
                Robot.makeDark();
                Robot.move();
            }
            else
            {
                if(isRightDark())
                {
                    turnRight();
                    Robot.move();
                    Robot.makeDark();
                    Robot.move();
                }
                else
                {
                    Robot.move();
                }
            }
        }
    }
  }
  //pre: The bot starts at the start of the connect the dots.
  //post: The bot ends one square after the connect the dots with all of the dots making a continuous line.
  public static void testConnectDots1()
  {
    Robot.load("connect1.txt");
    Robot.setDelay(0.025);
    connectDots();
  }
  
  public static void testConnectDots2()
  {
    Robot.load("connect2.txt");
    Robot.setDelay(0.025);
    connectDots();
  }
}
