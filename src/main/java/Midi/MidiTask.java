package Midi;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;


/**
 * Created by mks4b_000 on 12/8/2015.
 */
public class MidiTask implements Runnable {

    int channel = 0; //0 is piano
    int note = 0; //note pitch
    int volume = 0; //between 0 and 127
    int duration = 0;//Milliseconds of duration

    Synthesizer synth;

    MidiChannel[] channels;

    //Initialize in class loader
    {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MidiTask(int channel, int note, int volume, int duration) {
        this.channel = channel;
        this.note = note;
        this.volume = volume;
        this.duration = duration;
    }

    @Override
    public void run() {
        channels[channel].noteOn(note, volume); // C
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channels[channel].noteOff(note);
    }

    public static void main(String[] args) {

        int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

        int volume = 80; // between 0 et 127
        int duration = 200; // in milliseconds

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();

            // --------------------------------------
            // Play a few notes.
            // The two arguments to the noteOn() method are:
            // "MIDI note number" (pitch of the note),
            // and "velocity" (i.e., volume, or intensity).
            // Each of these arguments is between 0 and 127.
            channels[channel].noteOn(60, volume); // C note
            Thread.sleep(duration);
            channels[channel].noteOff(60);
            channels[channel].noteOn(62, volume); // D note
            Thread.sleep(duration);
            channels[channel].noteOff(62);
            channels[channel].noteOn(64, volume); // E note
            Thread.sleep(duration);
            channels[channel].noteOff(64);

            Thread.sleep(500);

            // --------------------------------------
            // Play a C major chord.
            channels[channel].noteOn(60, volume); // C
            channels[channel].noteOn(64, volume); // E
            channels[channel].noteOn(67, volume); // G
            Thread.sleep(3000);
            channels[channel].allNotesOff();
            Thread.sleep(500);


            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


