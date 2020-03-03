This app can be used to run commands on openpilot. Currently to run the commands, you will need to edit the file /data/openpilot/selfdrive/athenad.py and add the following method:

@dispatcher.add_method
def custom(command):
  cmd = "sh /storage/emulated/0/custom.sh %s" % command
  os.system(cmd)
  return {'command': command}
  
  
You will also need to add a new file on your eon at /storage/emulated/0/custom.sh that contains:

#!/usr/bin/bash

case "$1" in
	"autoecu")
		cd /data/openpilot/selfdrive/boardd
		pkill ./manager.py
		pm disable ai.comma.plus.offroad
		pm disable ai.comma.plus.frame
		am start -n com.android.chrome/com.google.android.apps.chrome.Main -d autoecu.io
    ;;
	"gitpull")
		cd /data/openpilot && git pull
	"terminal")
		pm disable ai.comma.plus.offroad
		pm disable ai.comma.plus.frame
		am start jackpal.androidterm/jackpal.androidterm.Term
	;;
	"android_settings")
		am start -a android.settings.SETTINGS
	;;
	"killall")
		pkill settings
		pkill chrome
		pkill androitterm
		pm enable ai.comma.plus.offroad
		pm enable ai.comma.plus.frame
		am start -n ai.comma.plus.frame/.MainActivity
	;;
	*)
		echo "command not found"
    ;;
esac




This should be all you need to do. The process will be easier once I have time to do stuff.
