#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#define _USE_MATH_DEFINES
#include <math.h>
#include <iostream>


float teapot_size = 0.7;
float eyeX = 0.0;
float eyeY = 0.0;
float eyeZ = 5.0;
float angle = 0;

void changeSize(int w, int h) {

	// Prevent a divide by zero, when window is too short
	// (you cant make a window with zero width).
	if(h == 0)
		h = 1;

	// compute window's aspect ratio 
	float ratio = w * 1.0 / h;

	// Set the projection matrix as current
	glMatrixMode(GL_PROJECTION);
	// Load Identity Matrix
	glLoadIdentity();
	
	// Set the viewport to be the entire window
    glViewport(0, 0, w, h);

	// Set perspective
	gluPerspective(45.0f ,ratio, 1.0f ,1000.0f);

	// return to the model view matrix mode
	glMatrixMode(GL_MODELVIEW);
}

void keyPress(unsigned char key, int x, int y) {
    if(key == 'a') {
        angle -= 0.5;
        eyeX += sin(angle);
        eyeZ += cos(angle);
    }
    if(key == 'd') {
        angle += 0.5;
        eyeX -= sin(angle);
        eyeZ -= cos(angle);
    }
}


void renderScene(void) {

	// clear buffers
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// set the camera
	glLoadIdentity();
	gluLookAt(eyeX, eyeY, eyeZ,
		      0.0,0.0,0.0,
			  0.0f,1.0f,0.0f);


    glutWireTeapot(teapot_size);



	// End of frame
	glutSwapBuffers();

}




int main(int argc, char **argv) {

    glutInit(&argc, argv);
    glutInitWindowPosition( 37, 42);
    glutInitWindowSize(800, 800);
    glutCreateWindow("Test");
    glutDisplayFunc(renderScene);
    glutReshapeFunc(changeSize);
    glutKeyboardFunc(keyPress);
    glutIdleFunc(renderScene);



// OpenGL settings 
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_CULL_FACE);
	glClearColor(0.0f,0.0f,0.0f,0.0f);



// enter GLUT's main loop
	glutMainLoop();
	
	return 1;
}

