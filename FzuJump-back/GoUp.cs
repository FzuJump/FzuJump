using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class GoUp : MonoBehaviour
{
    public GameObject PlayerController;
    
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.tag == "Player")
        {
            PlayerController.GetComponent<PlayerController>(). Is_Go_Up = true;
            
        }
    }
}
