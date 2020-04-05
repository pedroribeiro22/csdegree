#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#include <time.h>

#endif

#define _USE_MATH_DEFINES

float alfa = 0.0f, beta = 0.5f, radius = 100.0f;
float camX, camY, camZ;
int seed;


void spherical2Cartesian() {

	camX = radius * cos(beta) * sin(alfa);
	camY = radius * sin(beta);
	camZ = radius * cos(beta) * cos(alfa);
}


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



void renderScene(void) {

	// clear buffers
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// set the camera
	glLoadIdentity();
	gluLookAt(camX, camY, camZ,
		0.0, 0.0, 0.0,
		0.0f, 1.0f, 0.0f);

	glColor3f(0.2f, 0.8f, 0.2f);
	glBegin(GL_TRIANGLES);
		glVertex3f(100.0f, 0, -100.0f);
		glVertex3f(-100.0f, 0, -100.0f);
		glVertex3f(-100.0f, 0, 100.0f);

		glVertex3f(100.0f, 0, -100.0f);
		glVertex3f(-100.0f, 0, 100.0f);
		glVertex3f(100.0f, 0, 100.0f);
	glEnd();

    glColor3f(0.4285,0.1430,0.4285);
	glutSolidTorus(2,8,100,100);

	//DRAW THE 2 CIRCLES OF TEAPOTS
    float innerInc = M_PI/4;
    float outerInt=M_PI/8;

	glPushMatrix();
	glColor3f(0,0,1);
	glTranslatef(0,2,0);

	for(int i = 0; i<8;i++){

	    glPushMatrix();
        glTranslatef(15*cos(innerInc*i),0,15*sin(innerInc*i));
        glRotatef(-45*i,0,1,0);
        glutSolidTeapot(2);
        glPopMatrix();
	}
    glColor3f(1,0,0);
	for(int j = 0; j<16; j++){
        glPushMatrix();
        glTranslatef(35*cos(outerInt*j),0,35*sin(outerInt*j));
        glRotatef(-22.5*j,0,1,0);
        glutSolidTeapot(2);
        glPopMatrix();
	}
	glPopMatrix();

    //DRAW THE TREES
    srand(seed);
    int num_trees = 200;
    glPushMatrix();

    while(num_trees > 0){
        glPushMatrix();
        double x = -100 + rand()%200;
        double y = -100 + rand()%200;

        if( sqrt(pow(x,2) + pow(y,2) ) > 50){

            glTranslatef(x,0,y);

            glPushMatrix();

            glColor3f(0.5882,0.2941,0);
            glRotatef(-90,1,0,0);
            glutSolidCone(0.5,2,10,10);

            glPopMatrix();

            glTranslatef(0,1.5,0);

            glPushMatrix();

            glColor3f(0,1,0);
            glRotatef(-90,1,0,0);
            glutSolidCone(2,6,10,10);

            glPopMatrix();
            num_trees--;
        }
        glPopMatrix();
    }

    glPopMatrix();
	// End of frame
	glutSwapBuffers();
}


void processKeys(unsigned char c, int xx, int yy) {

// put code to process regular keys in here

}


void processSpecialKeys(int key, int xx, int yy) {

	switch (key) {

	case GLUT_KEY_RIGHT:
		alfa -= 0.1; break;

	case GLUT_KEY_LEFT:
		alfa += 0.1; break;

	case GLUT_KEY_UP:
		beta += 0.1f;
		if (beta > 1.5f)
			beta = 1.5f;
		break;

	case GLUT_KEY_DOWN:
		beta -= 0.1f;
		if (beta < -1.5f)
			beta = -1.5f;
		break;

	case GLUT_KEY_PAGE_DOWN: radius -= 1.0f;
		if (radius < 1.0f)
			radius = 1.0f;
		break;

	case GLUT_KEY_PAGE_UP: radius += 1.0f; break;
	}
	spherical2Cartesian();
	glutPostRedisplay();

}


void printInfo() {

	printf("Vendor: %s\n", glGetString(GL_VENDOR));
	printf("Renderer: %s\n", glGetString(GL_RENDERER));
	printf("Version: %s\n", glGetString(GL_VERSION));

	printf("\nUse Arrows to move the camera up/down and left/right\n");
	printf("Home and End control the distance from the camera to the origin");
}


int main(int argc, char **argv) {
    srand(time(nullptr));
    seed = rand();

// init GLUT and the window
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DEPTH|GLUT_DOUBLE|GLUT_RGBA);
	glutInitWindowPosition(100,100);
	glutInitWindowSize(800,800);
	glutCreateWindow("CG@DI-UM");
		
// Required callback registry 
	glutDisplayFunc(renderScene);
	glutReshapeFunc(changeSize);
	
// Callback registration for keyboard processing
	glutKeyboardFunc(processKeys);
	glutSpecialFunc(processSpecialKeys);

//  OpenGL settings
	glEnable(GL_DEPTH_TEST);
	//glEnable(GL_CULL_FACE);

	spherical2Cartesian();

	printInfo();

// enter GLUT's main cycle
	glutMainLoop();
	
	return 1;
}
