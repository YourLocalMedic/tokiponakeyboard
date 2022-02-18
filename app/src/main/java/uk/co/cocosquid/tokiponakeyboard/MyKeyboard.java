package uk.co.cocosquid.tokiponakeyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.preference.PreferenceManager;

import java.util.HashMap;

public class MyKeyboard extends MyKeyboardAbstract {

    // Word construction
    private boolean inBrackets = false;
    private String compoundFirstWordShortcut = "";
    private String suffix = "";

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {

        // Load shared preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);

        // Set the keys
        keys[0] = findViewById(R.id.ali);
        keys[1] = findViewById(R.id.en);
        keys[2] = findViewById(R.id.ike);
        keys[3] = findViewById(R.id.jan);
        keys[4] = findViewById(R.id.kama);
        keys[5] = findViewById(R.id.la);
        keys[6] = findViewById(R.id.ma);
        keys[7] = findViewById(R.id.nimi);
        keys[8] = findViewById(R.id.o);
        keys[9] = findViewById(R.id.pi);
        keys[10] = findViewById(R.id.sina);
        keys[11] = findViewById(R.id.tawa);
        keys[12] = findViewById(R.id.utala);
        keys[13] = findViewById(R.id.wile);

        keys[14] = findViewById(R.id.a);
        keys[15] = findViewById(R.id.ala);
        keys[16] = findViewById(R.id.e);
        keys[17] = findViewById(R.id.li);
        keys[18] = findViewById(R.id.mi);
        keys[19] = findViewById(R.id.ni);
        keys[20] = findViewById(R.id.pona);
        keys[21] = findViewById(R.id.toki);

        keys[22] = findViewById(R.id.bracket);
        keys[23] = findViewById(R.id.quote);
        keys[24] = findViewById(R.id.dot);
        keys[25] = findViewById(R.id.question);
        keys[26] = findViewById(R.id.enter);
        keys[27] = findViewById(R.id.delete);

        setDeleteListener(keys[27]);

        // Set colours
        setColours();

        // Set key listeners
        for (Button key : keys) {
            key.setOnClickListener(this);
            key.setOnLongClickListener(this);
            key.setTextSize(18);
        }

        stringToSitelenPona = new HashMap<String, String>()
        {{
            put("a", "󱤀");
            put("akesi", "󱤁");
            put("ala", "󱤂");
            put("alasa", "󱤃");
            put("ale", "󱤄");
            put("anpa", "󱤅");
            put("ante", "󱤆");
            put("anu", "󱤇");
            put("awen", "󱤈");
            put("e", "󱤉");
            put("en", "󱤊");
            put("esun", "󱤋");
            put("ijo", "󱤌");
            put("ike", "󱤍");
            put("ilo", "󱤎");
            put("insa", "󱤏");
            put("jaki", "󱤐");
            put("jan", "󱤑");
            put("jelo", "󱤒");
            put("jo", "󱤓");
            put("kala", "󱤔");
            put("kalama", "󱤕");
            put("kama", "󱤖");
            put("kasi", "󱤗");
            put("ken", "󱤘");
            put("kepeken", "󱤙");
            put("kili", "󱤚");
            put("kiwen", "󱤛");
            put("ko", "󱤜");
            put("kon", "󱤝");
            put("kule", "󱤞");
            put("kulupu", "󱤟");
            put("kute", "󱤠");
            put("la", "󱤡");
            put("lape", "󱤢");
            put("laso", "󱤣");
            put("lawa", "󱤤");
            put("len", "󱤥");
            put("lete", "󱤦");
            put("li", "󱤧");
            put("lili", "󱤨");
            put("linja", "󱤩");
            put("lipu", "󱤪");
            put("loje", "󱤫");
            put("lon", "󱤬");
            put("luka", "󱤭");
            put("lukin", "󱤮");
            put("lupa", "󱤯");
            put("ma", "󱤰");
            put("mama", "󱤱");
            put("mani", "󱤲");
            put("meli", "󱤳");
            put("mi", "󱤴");
            put("mije", "󱤵");
            put("moku", "󱤶");
            put("moli", "󱤷");
            put("monsi", "󱤸");
            put("mu", "󱤹");
            put("mun", "󱤺");
            put("musi", "󱤻");
            put("mute", "󱤼");
            put("nanpa", "󱤽");
            put("nasa", "󱤾");
            put("nasin", "󱤿");
            put("nena", "󱥀");
            put("ni", "󱥁");
            put("nimi", "󱥂");
            put("noka", "󱥃");
            put("o", "󱥄");
            put("olin", "󱥅");
            put("ona", "󱥆");
            put("open", "󱥇");
            put("pakala", "󱥈");
            put("pali", "󱥉");
            put("palisa", "󱥊");
            put("pan", "󱥋");
            put("pana", "󱥌");
            put("pi", "󱥍");
            put("pilin", "󱥎");
            put("pimeja", "󱥏");
            put("pini", "󱥐");
            put("pipi", "󱥑");
            put("poka", "󱥒");
            put("poki", "󱥓");
            put("pona", "󱥔");
            put("pu", "󱥕");
            put("sama", "󱥖");
            put("seli", "󱥗");
            put("selo", "󱥘");
            put("seme", "󱥙");
            put("sewi", "󱥚");
            put("sijelo", "󱥛");
            put("sike", "󱥜");
            put("sin", "󱥝");
            put("sina", "󱥞");
            put("sinpin", "󱥟");
            put("sitelen", "󱥠");
            put("sona", "󱥡");
            put("soweli", "󱥢");
            put("suli", "󱥣");
            put("suno", "󱥤");
            put("supa", "󱥥");
            put("suwi", "󱥦");
            put("tan", "󱥧");
            put("taso", "󱥨");
            put("tawa", "󱥩");
            put("telo", "󱥪");
            put("tenpo", "󱥫");
            put("toki", "󱥬");
            put("tomo", "󱥭");
            put("tu", "󱥮");
            put("unpa", "󱥯");
            put("uta", "󱥰");
            put("utala", "󱥱");
            put("walo", "󱥲");
            put("wan", "󱥳");
            put("waso", "󱥴");
            put("wawa", "󱥵");
            put("weka", "󱥶");
            put("wile", "󱥷");
            put("namako", "󱥸");
            put("kin", "󱥹");
            put("oko", "󱥺");
            put("kipisi", "󱥻");
            put("leko", "󱥼");
            put("monsuta", "󱥽");
            put("tonsi", "󱥾");
            put("jasima", "󱥿");
            put("kijetesantakalu", "󱦀");
            put("soko", "󱦁");
            put("meso", "󱦂");
            put("epiku", "󱦃");
            put("kokosila", "󱦄");
            put("lanpan", "󱦅");
            put("n", "󱦆");
            put("misikeke", "󱦇");
            put("ku", "󱦈");
            put("[", "󱦐");
            put("]", "󱦑");
            put("[]", "󱦐󱦑");
            put("_", "󱦒");
            put("pake", "󱦠");
            put("apeja", "󱦡");
            put("majuna", "󱦢");
            put("powe", "󱦣");

        }};


        // Set the button strings
        keyValues.put(R.id.ali, "a");
        keyValues.put(R.id.en, "e");
        keyValues.put(R.id.ike, "i");
        keyValues.put(R.id.jan, "j");
        keyValues.put(R.id.kama, "k");
        keyValues.put(R.id.la, "l");
        keyValues.put(R.id.ma, "m");
        keyValues.put(R.id.nimi, "n");
        keyValues.put(R.id.o, "o");
        keyValues.put(R.id.pi, "p");
        keyValues.put(R.id.sina, "s");
        keyValues.put(R.id.tawa, "t");
        keyValues.put(R.id.utala, "u");
        keyValues.put(R.id.wile, "w");

        keyValues.put(R.id.a, "a%");
        keyValues.put(R.id.ala, "ala%");
        keyValues.put(R.id.e, "e%");
        keyValues.put(R.id.li, "li%");
        keyValues.put(R.id.mi, "mi%");
        keyValues.put(R.id.ni, "ni%");
        keyValues.put(R.id.pona, "pona%");
        keyValues.put(R.id.toki, "toki%");

        keyValues.put(R.id.bracket, "%[");
        keyValues.put(R.id.dot, "%.");
        keyValues.put(R.id.quote, "%“");
        keyValues.put(R.id.question, "%?");
        keyValues.put(R.id.enter, "%enter");

        keyValues.put(R.id.delete, "%delete");

        // Arrays from xml
        Resources res = getResources();
        shortcuts = res.getStringArray(R.array.shortcuts);
        words = res.getStringArray(R.array.words);
        unofficialWords = res.getStringArray(R.array.unofficial_words);
    }

    protected void action(String startKey, String endKey) {
        if (endKey == null) {

            // Single key sent
            boolean nothingWritten = false;
            if (getPreviousCharacter().equals("“") && !getNextCharacter().isEmpty() && !getNextCharacter().equals("”") && !startKey.equals("%“") && !startKey.equals("%delete") && !startKey.equals("%enter")) {
                suffix = " ";
            }
            if (startKey.charAt(0) == '%') {

                // Special key sent
                if (!startKey.equals("%delete")) {
                    finishAction("finish");
                    if (inBrackets && !startKey.equals("%]") && !startKey.equals("%[")) {
                        action("%]", null);
                    }
                }
                switch (startKey) {
                    case "%]":

                        // Move cursor to the next space (or the end of the input if none are found)
                        int endBracketLocation = getEndBracketLocation();
                        inputConnection.setSelection(endBracketLocation, endBracketLocation);

                        // Place a closing bracket if it is missing
                        if (currentText.charAt(endBracketLocation - 1) != ']') {
                            write("]");
                        }

                        setBracket(false);
                        break;
                    case "%[":
                        if (inBrackets) {
                            action("%]", null);
                        } else {
                            writeShortcut("[%");

                            // Move cursor inside brackets
                            moveCursorBack(1);

                            setBracket(true);
                        }
                        break;
                    case "%“":
                        if (quoteNestingLevel > 0) {
                            write("”");
                            if (!",.:?!\n".contains(getNextCharacter()) && !getNextCharacter().isEmpty()) {
                                write(" ");
                            }
                        } else {
                            writeShortcut("“%");
                            if (getNextCharacter().equals(" ")) {
                                inputConnection.deleteSurroundingText(0, 1);
                            }
                        }
                        break;
                    case "%.":
                    case "%?":
                        write(Character.toString(startKey.charAt(1)));
                        //action(startKey.charAt(1) + "%", null);
                        break;
                    case "%delete":
                        delete();
                        break;
                    case "%enter":
                        enter();
                        break;
                    default:
                        Log.e(null, "Shortcut: " + startKey + " is not a special key");
                }

            } else {

                // Letter/word key sent
                if (doesShortcutExist(currentShortcut + startKey)) {

                    // Key is part of previous action and it is now finished
                    writeShortcut(currentShortcut + startKey);
                    currentShortcut = "";
                    setLayout("");

                } else if (doesShortcutExist(finishShortcut(currentShortcut + startKey))) {

                    // Key is part of previous action but it is still unfinished
                    currentShortcut += startKey;
                    setLayout("");
                    setLayout(currentShortcut);
                    nothingWritten = true;

                } else {

                    // Need to finish previous action
                    finishAction("finish");
                    if (doesShortcutExist(startKey)) {

                        // Word key sent
                        writeShortcut(startKey);

                    } else {

                        // Letter key sent
                        currentShortcut = startKey;
                        setLayout(currentShortcut);
                    }
                }
            }
            if (!suffix.isEmpty() && !nothingWritten) {
                moveCursorBack(1);
                suffix = "";
            }
        } else {

            // Two keys sent
            if (startKey.charAt(0) == '%' || endKey.charAt(0) == '%') {

                // Two separate keys to be sent (at least one was a special key)
                if (startKey.equals(endKey)) {

                    // Long press actions for special keys
                    finishAction("finish");
                    switch (startKey) {
                        case "%[":
                            write(",");
                            break;
                        case "%“":
                            if (quoteNestingLevel > 0) {
                                writeShortcut("“%");
                                if (getNextCharacter().equals(" ")) {
                                    inputConnection.deleteSurroundingText(0, 1);
                                }
                            } else {
                                action(startKey, null);
                            }
                            break;
                        case "%.":
                            write(":");
                            break;
                        case "%?":
                            write("!");
                            break;
                        case "%enter":
                            inputMethodManager.showInputMethodPicker();
                            break;
                        default:
                            Log.e(null, "Shortcut: " + startKey + " is not a special key");
                    }
                } else if (startKey.charAt(0) == '%') {

                    // Special key followed by normal key
                    if (startKey.equals("%enter")) {

                        // Switch subtype
                        finishAction("finish");
                        if ("aeijklmnopstuwAEIJKLMNOPSTUW".contains(getPreviousCharacter())) {
                            write(" ");
                        }
                        inputMethodService.setEmojiMode(true);

                    } else {
                        action(startKey, null);
                        action(endKey, null);
                    }

                } else {

                    // Normal key followed by special key
                    action(startKey, null);
                    finishAction("finish");
                    action(endKey, null);
                }
            } else {

                // A compound glyph to be sent (Both were letter/word keys)
                finishAction(startKey);
                compoundFirstWordShortcut = finishShortcut(currentShortcut + startKey);
                currentShortcut = "";
                action(endKey, null);
            }
        }
    }

    // Returns true if the cursor is at the start of an input, newline or quote
    private boolean cursorAtStart() {
        updateTextInfo();
        if (beforeCursorText.length() == 0) {
            return true;
        } else {
            String previousCharacter = getPreviousCharacter();
            return "\n“".contains(previousCharacter);
        }
    }

    protected void delete() {
        if (currentShortcut.isEmpty()) {

            // Delete some text (wow this is much easier huh)
            updateTextInfo();
            inputConnection.deleteSurroundingText(2, 0);
        } else {

            // Cancel current input in progress
            currentShortcut = "";
            compoundFirstWordShortcut = "";
            setLayout("");
        }
    }

    /* nextKey allows the function to know whether or not to reset currentShortcut in the cases
     * where a new compound glyph has been started.
     */
    public void finishAction(String nextKey) {
        boolean validCombination = doesShortcutExist(finishShortcut(currentShortcut + nextKey));
        if (!validCombination || !compoundFirstWordShortcut.isEmpty()) {
            writeShortcut(finishShortcut(currentShortcut));
            if (!validCombination) {
                currentShortcut = "";
            }
            setLayout("");
        }
    }

    private int getEndBracketLocation() {
        updateTextInfo();
        int endBracket = beforeCursorText.length() - 1;
        while (true) {
            if (currentText.charAt(endBracket) == ']' || endBracket == currentText.length() - 1) {

                // A bracket was found or the end of the input was reached
                return endBracket + 1;

            }
            endBracket++;
        }
    }

    public void loadPreferences() {
        setColours();
    }

    public void setBracket(boolean newInBrackets) {
        inBrackets = newInBrackets;
        if (inBrackets) {
            ((Button) findViewById(R.id.bracket)).setText("]");
        } else {
            ((Button) findViewById(R.id.bracket)).setText("[");
        }
    }

    public void setColours() {
        super.setColours();
        for (int i = 0; i < keys.length; i++) {
            // Set base colours
            if (i < 14) {

                // Letter keys
                keys[i].setBackgroundTintList(ColorStateList.valueOf(letterKeyColour));
                keys[i].setTextColor(letterKeyTextColour);

            } else if (i < 22) {

                // Common word keys
                keys[i].setBackgroundTintList(ColorStateList.valueOf(commonWordKeyColour));
                keys[i].setTextColor(commonWordKeyTextColour);

            } else {

                // Special keys
                keys[i].setBackgroundTintList(ColorStateList.valueOf(specialKeyColour));
                keys[i].setTextColor(specialKeyTextColour);
            }
        }

        // Set background colour
        findViewById(R.id.keyboard).setBackgroundColor(backgroundColour);
    }

    public void updateCurrentState() {

        // Get the adjacent characters
        String charOnRight = getNextCharacter();
        String charOnLeft = getPreviousCharacter();

        boolean adjust = true;
        if ("],”.:?!\n".contains(charOnLeft) || " _],”.:?!\n".contains(charOnRight)) {

            // Do not adjust cursor position
            adjust = false;
        }

        int moveTo = 0;
        int i;
        label:
        for (i = beforeCursorText.length() - 1; i >= 0; i--) {
            String currentString = Character.toString(beforeCursorText.charAt(i));
            switch (currentString) {
                case "“":
                    if (moveTo == 0) {
                        moveTo = i + 1;
                    }
                    break;
                case "\n":
                    if (moveTo == 0) {
                        moveTo = i + 1;
                    }
                    setBracket(false);
                    break label;
                case " ":
                case "]":
                    setBracket(false);
                    break label;
                case "_":
                case "[":
                    setBracket(true);
                    break label;
            }
            if (i == 0) {
                setBracket(false);
                break;
            }
        }
        if (adjust) {
            if (moveTo == 0) {
                moveTo = i;
            }
            inputConnection.setSelection(moveTo, moveTo);
        }

        // Ensure the correct quote is on the key
        updateQuoteNestedLevel();
        if (quoteNestingLevel > 0) {
            ((Button) findViewById(R.id.quote)).setText("”");
        } else {
            ((Button) findViewById(R.id.quote)).setText("“");
        }
    }

    private void write(String toWrite) {
        if (inBrackets && ",:!".contains(toWrite)) {
            action("%]", null);
        }
        inputConnection.commitText(toWrite + suffix, 1);
    }

    private void writeShortcut(String shortcut) {

        // Do not write anything if the shortcut is empty
        if (shortcut.isEmpty()) {
            return;
        }

        // Decide the correct word spacer to put before the word
        String wordSpacer = "";
        if (cursorAtStart()) {
            wordSpacer = "";
        } else if (inBrackets) {
            wordSpacer = "_";
        }

        // Prepare first part of a compound glyph if it exists
        String compoundFirstWord = "";
        if (!compoundFirstWordShortcut.isEmpty()) {
            compoundFirstWord = getWord(compoundFirstWordShortcut) + "-";
            compoundFirstWordShortcut = "";
        }

        write(wordSpacer + compoundFirstWord + getWord(shortcut));
    }
}