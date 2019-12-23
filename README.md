# foregroundServiceCrashOreo
Поймали краш "Context.startForegroundService() did not then call Service.startForeground()" в API >= Oreo ? 
Крепитесь, мужики. 

Оригинальная бага: https://issuetracker.google.com/issues/7611207



Удалось найти баг на issuetracker который был закрыт гуглом т.к. так и было задуманно: https://issuetracker.google.com/issues/76112072
По ходу дискуссии гугловцы показали себя настоящими мужиками:
Их трактовка
>After the system has created the service, the app has five seconds to call the service's startForeground() method to show the new service's user-visible notification. If the app does not call startForeground() within the time limit, the system stops the service and declares the app to be ANR.

На самом деле означает что показывать нотификацию в foregroundServices для >Oreo нужно 
<b>ВСЕГДА</b>

Из этого можно сделать выводы:
- не делайте, ребята, остановку foreground service до показа нотификации и вызова startForeground()  это приведет к крашу
- перезапускать сервис вообще плохая идея
- но если уж очень надо сервис перезапустить, то добавляем .debounce(5000L) на start/stop. Хреново? Да, ну а че поделать
- надо перезапустить вот прямо сейчас??! Для вас есть лайфхак - покажите пустую нотификацию перед остановкой сервиса 

<img src="https://www.meme-arsenal.com/memes/615dc2dfdc3914c5e9d0ece48ee3c679.jpg" alt="Smiley face" height="362" width="545">
