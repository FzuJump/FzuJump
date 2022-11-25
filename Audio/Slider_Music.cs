using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Audio;
using UnityEngine.UI;

public class Slider_Music : MonoBehaviour
{
    public AudioMixer Music_Mixer;
    //public Slider Audio_Sli;

    public void SetAudioVolume(float volume)
    {
        Music_Mixer.SetFloat("Music_Volume", volume);
    }
}
