# dumb-thing-poorly-created
asdf;j
dumb
## credit thing
oh also i didn't create the bihashmap i googled and someone suggested this to someone else in the stackoverflow whereabouts so i OBTAINED via copy paste although desafortunadamente i forgot where i got it from

## weird testing methods on windows
for testing i usually use [this website's interactive rubik's cube](https://ruwix.com/online-rubiks-cube-solver-program/) primarily because it's hyperimmediate meanwhile ALL the other ones have 5 second long animations per turn

I utilize AHK to create a simple macro to iterate through all elements quickly, as I don't want to press a key 400 times

## example AHK script
```autohotkey
^j::
Loop, 84
{
  Send, dlf
  Sleep 10
}
return
```

## weird testing methods on mac
theres a cool thing called an applescript that can also simulate keypresses although it uses a slightly weirder syntax

i still used the same [interactive rubik's cube website](https://ruwix.com/online-rubiks-cube-solver-program/) since its too convenient to not use

i think there is a way to string together keypresses into a string for it to iterate through but im too lazy to find out so for now it utilizes the 1 by 1 basis; i just create a separate program to create applescript command thingies

if using firefox you can replace `google chrome` with `firefox` and probably `safari` if safari

if you're using edge i'll be quite confused

## example applescript
```applescript
activate application "Google Chrome"
repeat 77 times
	tell application "System Events"
		keystroke "r"
		delay 1.0E-3
		keystroke "l"
		delay 1.0E-3
		keystroke "l"
		delay 1.0E-3
		keystroke "u"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "l"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "d"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "u"
		delay 1.0E-3
		keystroke "f"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "f"
		delay 1.0E-3
		keystroke "l"
		delay 1.0E-3
		keystroke "d"
		delay 1.0E-3
		keystroke "u"
		delay 1.0E-3
		keystroke "r"
		delay 1.0E-3
		keystroke "b"
		delay 1.0E-3
		keystroke "f"
		delay 1.0E-3
		keystroke "f"
		delay 1.0E-3
	end tell
	delay 1.0E-3
end repeat
```

## bugs
🐛🐛🐛
