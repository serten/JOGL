package shader_example_texture;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * A minimal program that draws with JOGL in an AWT Frame.
 *
 * @author Wade Walker
 */
public class main_Controller_texture {

    private static FPSAnimator animator;

    static int  widthh=0;//(int) screenSize.getWidth();
    static int  heightt=0 ;//(int) screenSize.getHeight();
    
    //shader variables
    private static int shader;
    private static boolean useShader;
    
    private static boolean initialize;
    
    private static GL2 gl2;
    private static String PATH = "/Users/suleymanerten/Documents/workspace/GITHUB/JOGL/JOGL_EXAMPLE/src/shader_example_texture/";
    static float scale = 1.0f;
    
    public static void main( String [] args ) {

    	initialize = false;

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
       
        glcanvas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				scale = scale - 0.1f;
				//System.out.println("scale decreased" + scale);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        glcanvas.addGLEventListener( new GLEventListener() {
        	 Texture mTest = null;
             float vertices[] = {	
            		0.0f, 0.5f, .0f, 1.0f,
             		0.0f, 0.0f, .0f, 1.0f,  
         			0.5f, 0.5f, .0f, 1.0f,
         			0.5f, 0.0f, .0f, 1.0f};
             float textureCoord[] = {	
              		0.0f, 1.0f,   
          			0.0f, 0.0f, 
          			1.0f, 1.0f,
          			1.0f, 0.0f};
             
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
            	
            }
            
            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                System.out.println("init called");
                gl2 = glautodrawable.getGL().getGL2();
                shader_Init(gl2);
                
                
            }
            
            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
                System.out.println("dispose called");
            }
            
            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                //System.out.println("display called");

            	int scaleloc = 0;
            	if(!initialize)
            	{
            		initialize = true;
            		int[] idArray = new int[2];
            		// let's generate
            		gl2.glGenBuffers( 2, idArray, 0);
            		
            		int vecloc   = gl2.glGetAttribLocation(shader, "position");
            		System.out.println("vecloc:"+vecloc);
            		int textureloc = gl2.glGetAttribLocation(shader, "textureCoord");
            		System.out.println("textureloc:"+textureloc);
            		int sampleloc = gl2.glGetUniformLocation(shader, "myTexture"); 
            		System.out.println("sampleloc:"+sampleloc);
            		int widthloc =  gl2.glGetUniformLocation(shader, "width"); 
            		System.out.println("widthloc:"+widthloc);
            		int heightloc =  gl2.glGetUniformLocation(shader, "height"); 
            		System.out.println("heightloc:"+heightloc);
            		scaleloc =  gl2.glGetUniformLocation(shader, "scale"); 
            		System.out.println("scaleloc:"+scaleloc);
            		
            		
            		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, idArray[0]);
            		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8,
            				Buffers.newDirectFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
            		gl2.glEnableVertexAttribArray(vecloc);
            		gl2.glVertexAttribPointer(vecloc, 4, GL2.GL_FLOAT, false, 0, 0);
            		
            		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, idArray[1]);
            		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoord.length * Float.SIZE / 8,
            				Buffers.newDirectFloatBuffer(textureCoord), GL2.GL_STATIC_DRAW);
            		gl2.glEnableVertexAttribArray(textureloc);
            		gl2.glVertexAttribPointer(textureloc, 2, GL2.GL_FLOAT, false, 0, 0);
            		
            		try {
						mTest = TextureIO.newTexture(new File(PATH+"nature31.jpg"),true);
						gl2.glUniform1f(widthloc, (float) mTest.getWidth());
						gl2.glUniform1f(heightloc, (float) mTest.getHeight());
						
					} catch (GLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		int []texar = new int[1];
            		gl2.glEnable(GL2.GL_TEXTURE_2D);
            		gl2.glGenTextures(1, texar, 0);
            		gl2.glBindTexture(GL2.GL_TEXTURE_2D,texar[0]); 
            		mTest.enable(gl2);
            		mTest.bind(gl2);
            		gl2.glTexParameteri(gl2.GL_TEXTURE_2D, gl2.GL_TEXTURE_MAG_FILTER, gl2.GL_LINEAR);
            		gl2.glTexParameteri(gl2.GL_TEXTURE_2D, gl2.GL_TEXTURE_MIN_FILTER, gl2.GL_NEAREST);
            		//gl2.glGenerateMipmap(gl2.GL_TEXTURE_2D);
            	}
            	
            	if(useShader) 
        		{
            		
            		gl2.glUseProgramObjectARB(shader);
            		
            			
            			gl2.glUniform1f(scaleloc, scale);
            			System.out.println(scale);
	            		gl2.glActiveTexture(GL2.GL_TEXTURE0);
	            		mTest.enable(gl2);
	            		mTest.bind(gl2); 
	            		
	            		gl2.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, 4 );
	            		
	            		mTest.disable(gl2);

                	gl2.glUseProgramObjectARB(0);
        		}


            	
            	}
        });

        final Frame frame = new Frame( "TEXTURE MAPPING EXAMPLE" );
        
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                frame.remove( glcanvas );
                frame.dispose();
                System.exit( 0 );
            }
        });

        

        frame.setSize( 1000, 1000 );
        frame.setVisible( true );
        widthh = frame.getWidth();
        heightt =  frame.getHeight();
        animator = new FPSAnimator(160);
        animator.add(glcanvas);
        animator.start();
    }
    
    public  static void shader_Init(GL2 gl2)
    {
    	
    	//ask gl for a shader program to handle the vertex and fragment shaders

    	shader=gl2.glCreateProgram();

    	if(shader<1)System.out.println("Problem creating mainShader");

    	/*

    	* set up the vertex and fragment shaders. Bin contains static methods

    	* for setting up the shaders. These methods return the unique int identifiers

    	* GL assigns to each shader

    	*/

    	long mainVert=Bin.createVertShader(gl2, PATH+"v.txt");

    	long mainFrag=Bin.createFragShader(gl2, PATH+"f.txt");

    	//long mainGeom=Bin.createGeomShader(gl2, PATH+"g.txt"); //MAC doesnt support OPENGL 3.2

    	
    	System.out.println(mainVert);

    	//attach the shaders to the shader program and link

    	gl2.glAttachObjectARB(shader, mainVert);
    	
    	//gl2.glAttachObjectARB(shader, mainGeom); //MAC doesnt support OPENGL 3.2

    	gl2.glAttachObjectARB(shader, mainFrag);

    	gl2.glLinkProgramARB(shader);

    	//tell GL to validate the shader program and grab the created log

    	gl2.glValidateProgramARB(shader);

    	useShader=true;//Bin.printLogInfo(gl2, shader);
    }
}
