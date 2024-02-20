ffmpeg -y -i background-images-copyrighted/ritual-ornaments-4157521_1920.jpg -vf "drawtext=fontfile=C\\:/Windows/fonts/IMPACT.TTF:fontsize=55:fontcolor='white':bordercolor='black':borderw=5:rate=25:text='30 MINUTES MILITARY STYLE GUIDED TIMERS(EPIC MUSIC)':x=(w-text_w)/2:y=(h-text_h)/2 - 45" background-images-copyrighted/ritual-ornaments-4157521_1920-temp.jpeg




ffmpeg -y -i background-images-copyrighted/ritual-ornaments-4157521_1920-temp.jpeg -vf "drawtext=fontfile=C\\:/Windows/fonts/IMPACT.TTF:fontsize=115:fontcolor='yellow':bordercolor='black':borderw=5:rate=25:text='FOR 10X PRODUCTIVITY':x=(w-text_w)/2:y=(h-text_h)/2 + 40" 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.jpeg




del background-images-copyrighted/ritual-ornaments-4157521_1920-temp.jpeg




BALABOLKA\balcon -f script.txt -w 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.mp3 -srt 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.srt -n "Microsoft David Desktop"  -s 0 -v 100 -p -4




ffmpeg -y -i 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.mp3 -stream_loop -1 -i background-music/honor-and-sword-main-11222.mp3 -filter_complex amix=inputs=2:duration=1:dropout_transition=0:weights="1 0.25":normalize=0 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp3




del 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.mp3 
ffmpeg -y -loop 1 -i 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.jpeg -i 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp3 -c:v libx264 -tune stillimage -c:a aac -b:a 192k -pix_fmt yuv420p -shortest 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp4




del 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp3 
ffmpeg -y -i 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp4 -lavfi "subtitles=30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.srt:force_style='Fontname=fonts/IMPACT.TTF,Alignment=6,OutlineColour=&H80000000,BackColour=&H80000000,BorderStyle=3,Outline=2,Shadow=2,Fontsize=45'" -crf 1 -c:a copy 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.mp4




del 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY-bg56.mp4 30-MINUTES-MILITARY-STYLE-GUIDED-COUNTDOWN-TIMER-WITH-EPIC-MUSIC-FOR-10X-PRODUCTIVITY56.srt