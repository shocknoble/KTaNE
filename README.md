# SHoEE a.k.a Shut Up or Everyone Explodes
#### Image Recognition Java project to complete instances of the game 'Keep Talking and Nobody Explodes'
[Video Example of a partial solve](https://i.imgur.com/WeflhxZ.mp4)

[2nd Example with a more impressive maze solve](https://i.imgur.com/tYULR9O.mp4)
## NOTICE: 
By reading this README file you recognize and accept that I am exempt from any and all criticisms of the code contained within this repository. This is a self driven project to showcase the SikuliX library, and to push myself to actually finish a project and maintain a repository. Instructions for how to set up the game are below.





## Setting up your KTaNE game for automation:

#### Required:
- SikuliX 2.0.1 API (available here: https://launchpad.net/sikuli/sikulix/2.0.1/+download/sikulixapi-2.0.1.jar)
- Manual reference number 241
- 1920x1080 resolution
- Fullscreen (ideally on ONE 1080 screen)
- Accessibility Options:
	- Disable these three modules:
		- Simon Says
		- Complex Wires
		- Wire Sequences
		
		
#### Starting a game:
From the home screen, the script will take control, choose a free play session, and set the timer and modules to max. 
Once launched and after the lights come on, a java swing panel will open asking you for the number of batteries on the bomb, the last digit of the serial number, and whether or not there are lit CAR & FRK labels.

Upon answering these prompts, make sure your bomb is centered on the screen with no module selected before clicking the start button.




## Current problems
- Lights going out ruins *some* modules, but not all. 
- If a module fails and results in a strike, any remaining morse code/button modules will be ruined so just fight for your life to regain cursor control.
- The passwords module occasionally reads letters like O/Q, I/T, as the other letter. This ONLY results in a problem when it does this during the final spelling out of the word.
- Basically everything else ¯\\\_(ツ)\_/¯
