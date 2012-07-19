ReadME for the Editor:

// Thur July 19, 2012

Fixed the grav flip so that you can increase i (or decrease init) and it will cycle through the images.
	- I guess this way you can create trippy patterns where they all start at different times?
Added all of the dimensions for the shapes (as defaultWidth and defaultHeight in Shape).
	- If unspecified, I have it set the height/width to 40x40.
	- Also note that you can still change the width height as much as you want. I can disable this if you like, but I don't think it hurts since you can just re-click to reset it.
Added a MAX and MIN_TYPE_SIZE (currently allows 0-25).
Added a lowRes mode for 1024x600. To enable, just set the first two constants (EDITOR_RESOLUTION_X and EDITOR_RESOLUTION_Y).


// Wed July 18, 2012
More changes.

Saving and loading works! No errors with it so far in my testing.

Controls: (that were added/changed since last time)
Left click on the instance ints to select them (they capitalize and change to =), then
[ to decrease currently selected int value.
] to increase currently selected int value.
Left click on any of the booleans to toggle them.
Left click on the bottom icons (no longer right click).
	- Additionally, the should be 'highlighted; with a small ^ beneath them.

Notes:
I need you to tell me the correct sizes to use for the 'locked' width/height shapes, then I'll add that. Should be very easy to do.
Fixed a bug right before pushing that would screw buttons up after translation. This build should be totally solid.

// Wed July 18, 2012
Well that changed quickly. Now it's feature rich, prettier, and pretty badass.

Layout:
Top bar has buttons (not currently active, but easy to implement).
Center is the game screen, set to correct resolution.
Over top the game screen there is a grid of adjustable size (constrained between MIN and MAX_GRID_SIZE).
To the left of the game screen there's debug info, mostly on the current piece (including all instance ints and booleans).
Below the game screen there are two rows of click-able icons (one for each of the classes) that will change the current shape to that kind.

Controls:
WASD - to translate the camera.
IJKL - to adjust the thickness of the current shape.
, - to decrease the grid thickness.
. - to increase the grid thickness.
T - to toggle the grid on or off.
CTRL-S - Save the level.
CTRL-O - Open a level.
Left click within the game screen to place the current shape.
	- For now, clicking outside does nothing to avoid accidentally placing shapes in an untranslated-to area.
Right click one of the bottom icons to select that type of shape.

Notes:
Things should be very stable, except I haven't fully implemented saving and loading. So right now if you try to load any level with more than just boxes, it'll fail.
I think my next plan is to be able to change the instance variables so you can get different types of clouds, words, etc.

And that's it for now. Pretty goddamn awesome. I'm happy. Taking a lunch break and then some more work.

// Tues July 17, 2012
Right now it's not feature-rich at all. What you can do is use the mouse to click and place objects
(by default the left-facing bag), translate the internal screen without messing up the outside frame (WASD),
save (control-S), load (control-O), and switch to the arrow (hit X).

Basically just a prototype, but I had a nice flash of insight about how to move on and I'm very excited to
get home tonight and implement it. I'm going to really flesh out the assignPic method, which together with the
abstract pic methods in Shape (only one file- not in any of the rest), can very very powerfully make things happen.

I'm excited. I think progress is going to happen quickly. 