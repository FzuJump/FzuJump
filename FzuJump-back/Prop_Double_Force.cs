using JetBrains.Annotations;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Prop_Double_Force : MonoBehaviour
{
    public GameObject PlayerController;
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.tag == "Player")
        {
            PlayerController.GetComponent<PlayerController>().IsDoublePressForce = true;//½«pressForce·­±¶ÉèÖÃÎªtrue
            PlayerController.GetComponent<PlayerController>().pressForce *= 2;
        }
    }
}
