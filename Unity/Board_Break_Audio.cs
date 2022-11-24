using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Board_Break_Audio : MonoBehaviour
{
    public AudioSource Board_Break_Music;
    private void OnCollisionExit2D(Collision2D collision)
    {
        Board_Break_Music.Play();
    }
}
