using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Prop_Audio : MonoBehaviour
{
    public AudioSource Prop_Positive_Music;
    public AudioSource Prop_Native_Music;
    
    
    public AudioSource Force_Music;

    private void OnTriggerEnter2D(Collider2D Collision)
    {
        if (Collision.tag == "Collection")
        {
            Prop_Positive_Music.Play();
        }
        if (Collision.tag == "Teacher")
        {
            Prop_Native_Music.Play();
        }
        
    }
    
}
