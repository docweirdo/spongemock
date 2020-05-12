# spongemock

This is a very simple android app, making use of the feature implemented with Android 6.0 to define
individual options for the Text Selection Toolbar. This application takes a selected text and translates
it to mock text, refering to the style known as sarcasm text or spongemock. 

The android theme is set to NoDisplay in the Android Manifest, the app isnt visible in the app launcher and has no gui.
It works by registering an intent filter for the text action intent in the android manifest. Upon receiving such an intent,
the activiys on create method executes the function that transforms the text passed along by the intent.
It then checks wether the selected text is editable. If it is, it returns the transformed text as an intent itself. 
If it isnt, the transformed text is passed along to the clipboard via a clipboard manager and a toast confirms the action to the user. 

i am aware of the gravity of my contribution to the open source community and will take honorary payments and expressions of gratitude after appointments via email.
