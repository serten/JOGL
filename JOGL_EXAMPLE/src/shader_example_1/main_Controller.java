package shader_example_1;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A minimal program that draws with JOGL in an AWT Frame.
 *
 * @author Wade Walker
 */
public class main_Controller {

    private static FPSAnimator animator;

    private static cube first_cube,second_cube;
    
    static int  widthh=0;//(int) screenSize.getWidth();
    static int  heightt=0 ;//(int) screenSize.getHeight();
    
    //shader variables
    private static int shader;
    private static boolean useShader;
    
    private static boolean initialize;
    
    private static GL2 gl2;
    
    private static String PATH = "/Users/suleymanerten/Documents/workspace/GITHUB/JOGL/JOGL_EXAMPLE/src/shader_example_1/";
    
    
    public static void main( String [] args ) {

    	initialize = false;

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
       


        glcanvas.addGLEventListener( new GLEventListener() {
             int counter=1000;
             float vertices[] = {	
             		0.8f, 0.2f, .0f, 1.0f,  
         			0.2f, 0.2f, .0f, 1.0f,
         			0.5f, 0.2f, .0f, 1.0f,
         			0.0f, 0.8f, .0f, 1.0f};
             
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                //OneTriangle.setup( glautodrawable.getGL().getGL2(), width, counter );
                //first_cube.setup( glautodrawable.getGL().getGL2(), width, height );
                //second_cube.setup( glautodrawable.getGL().getGL2(), width, height );
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

            	
            	if(!initialize)
            	{
            		initialize = true;
            		int[] idArray = new int[1];
            		// let's generate
            		gl2.glGenBuffers( 1, idArray, 0);
            		
            		int vecloc=gl2.glGetAttribLocation(shader, "position");
            		
            		gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER, idArray[0]);
            		gl2.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8,
            				Buffers.newDirectFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
            		gl2.glEnableVertexAttribArray(vecloc);
            		
            		
            		gl2.glVertexAttribPointer(vecloc, 4, GL2.GL_FLOAT, false, 0, 0);


        			
            		//gl2.glEnable(GL3.GL_PROGRAM_POINT_SIZE); //==> used with GL_POINTS
            		//gl2.glEnable(GL3.GL_VERTEX_PROGRAM_POINT_SIZE);
            		//gl2.glEnable(GL3.GL_POINT_SIZE); 
            		//gl2.glEnable(GL3.GL_LINE_WIDTH);
            		gl2.glLineWidth(15.0f);  //==> used with GL_LINES
            		gl2.glEnable(GL3.GL_LINE_SMOOTH);
            		
            		if(gl2.glIsEnabled(	GL3.GL_PROGRAM_POINT_SIZE))
            		{
            			System.out.println("enabled");
            		}
            		else
            		{
            			System.out.println("Not enabled!");
            		}

            	}
            	
            	if(useShader) 
        		{
            		
            		gl2.glUseProgramObjectARB(shader);
            		
            		gl2.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, 4);

                	gl2.glUseProgramObjectARB(0);
        		}


            	
            	
            	

            	
            
                //OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight());
                //first_cube.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight(),3f,-10.0f );
                //second_cube.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight(),-3f,-9.0f );
            }
        });

        final Frame frame = new Frame( "SHADER EXAMPLE" );
        
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                frame.remove( glcanvas );
                frame.dispose();
                System.exit( 0 );
            }
        });

        

        frame.setSize( 640, 480 );
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
