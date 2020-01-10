import java.applet.*; 
import java.awt.*; 
import java.awt.event.*;
import java.net.*;

public class spaceinvaders extends Applet implements Runnable, KeyListener 
{ 
  int appletx=1439, applety=838;
  int x=700, y=760, yradius=10, xradius=100, xsp=5, ysp=0, w=+(x+xradius);
  int mx=0, my=y, mxsp=0, mysp=0, count=0;
  int pic2=0;
  int move=0, a=0, threadcounter=0, b=0, down=0, c=0;
//  Alien[] aliens;
  Image alien1, alien2;
  int alien_xsize=40, alien_ysize=40;
  int alien_x=0, alien_y=30, alien_xsp=3, alien_ysp=2;
  int life=0, counter=0, die=0;
  int [] hit;
  
  private Image dbImage; 
  private Graphics dbg; 
  
  public void init()
  {
    alien1 = getImage(getCodeBase(), "alien1.png");
    alien2=getImage (getCodeBase (), "alien2.gif"); 
    addKeyListener(this);
//    aliens= new Alien[20];
//    for (int t=0;t<20;t++)
//    {
//      aliens[t]=new Alien();
//      aliens[t].create(alien1,alien2);
//      aliens[t].move();
//    }
    hit=new int[68];
    for(int w=0; w<68 ; w++) 
      hit[w]=0;
  }
  
  public void start(){     
    addKeyListener(this);
    Thread th=new Thread (this); 
    th.start (); 
    //  System.out.println(count);
  }
  
  public void stop() {} 
  
  public void destroy() {} 
  
  public void keyReleased (KeyEvent e){}   
  public void keyTyped(KeyEvent e){}  
  
  public void keyPressed (KeyEvent e)
  {
    int code =e.getKeyCode(); 
    //  System.out.print(code); 
    if (code == KeyEvent.VK_LEFT) 
    {
      x=+(x-5);
      x-=xsp;
    } 
    
    else if (code == KeyEvent.VK_RIGHT)   
    {           
      x=+(x+3);
      x+=xsp;
    }
    
    else if (code == KeyEvent.VK_SPACE)
    {
      count=1;
      mxsp=5;
      mysp=5;
    }
  }
  public void run () { 
    Thread.currentThread().setPriority(Thread.MIN_PRIORITY);  
    
    while(true)
    {
      if (b==0)
        move++;
      if (b==1)
      {  
        move--;
      }
      if (count==1)
      {
        my-=mysp;
       }
      
      if(mx>= a && mx<=a+xradius && my>=y && my<=y+alien_y && die==0)
      {
        hit[counter]=1;
       life=1;
       
        System.out.println("hit");
      }
      
      repaint();
      try 
      { 
        Thread.sleep (20); 
      } 
      catch (InterruptedException ex) 
      {
      } 
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
    }
  }
  
  public void paint (Graphics g) {
    
    g.setColor(Color.GREEN);
    g.fillRect(x, y, xradius, yradius);
    g.fillRect(x+15, y-10, 70, 10);
    g.fillRect(x+30,y-20,40,15);
    g.fillRect(x+43,y-30,15,15);
    if(count==1)
    {
      mx=+(x+50);
      g.drawLine(mx,my,mx,my+10);
    }
    
    if(die==1)
    {
      mysp=0;
      g.setColor(Color.BLACK);
    }
    setBackground(Color.BLACK);
    setSize(appletx,applety);
    
    counter=0;
    for ( a=0+move; a<1000+move; a+=45)
    {       
//      
      for (int y=0+down; y<100+down; y+=45)
      {           
//        System.out.println("counter"+counter);
//        System.out.print("y=="+y);
        if (pic2==0 && life ==0)
        {
          g.drawImage(alien1,a, y+6,alien_xsize,alien_ysize,this);
          pic2=1;
          life=0;
        }
        else if (pic2==1 && life ==0)
        {
          g.drawImage(alien2,a,y,alien_xsize+5,alien_ysize+20,this);
//          System.out.println("second picture");      
          pic2=0;
          life=0;
        }
        if (a+alien_xsize>=appletx)
        {       
          b=1;
          c=1;
          down=down+10;
        }
        else if(a<=0) 
        {
          b=0;
          down=down+10;
        }
        counter++;
die=0;
      }

   }
  }
  
  public void update (Graphics g) 
  { 
    if (dbImage == null) 
    { 
      dbImage = createImage (appletx, applety); 
      dbg = dbImage.getGraphics (); 
    } 
    
    dbg.setColor (getBackground ()); 
    dbg.fillRect (0, 0,appletx, applety); 
    
    dbg.setColor (getForeground()); 
    paint (dbg); 
    
    g.drawImage (dbImage, 0, 0, this); 
  } 
} 
