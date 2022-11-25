using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Audio;
using UnityEngine.UI;

public class Slider_Audio : MonoBehaviour
{
    public AudioMixer Audio_Mixer;
    //public Slider Audio_Sli;
    
    public void SetAudioVolume(float volume)
    {
        Audio_Mixer.SetFloat("Audio_Volume",volume);
    }
}
