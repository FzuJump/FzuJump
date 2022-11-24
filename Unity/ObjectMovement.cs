using System.Collections;
using System.Collections.Generic;
using System.Net;
using Unity.VisualScripting;
using UnityEditor;
using UnityEngine;

public class ObjectMovement : MonoBehaviour
{
    public float Speed = 0.1f;
    public float WaitTime = 0.1f;
    private float waitTime;
    public Vector3 Start_pos;
    public Vector3 End_pos;
    private int flag = 1;
    public Rigidbody2D Rd;
    public Collider2D collider;
    public AudioSource Fall_Music;
    void Start()
    {
        Start_pos = transform.position;
        End_pos = Start_pos;
        End_pos.x = Start_pos.x + 2.0f;
        waitTime = WaitTime;
    }


    void FixedUpdate()
    {
        if (flag == 1)
        {
            Vector2 StoE = (End_pos - Start_pos).normalized;
            gameObject.transform.Translate(StoE * Speed);
        }
        if (flag == -1)
        {
            Vector2 StoE = (Start_pos - End_pos).normalized;
            gameObject.transform.Translate(StoE * Speed);
        }
        if (transform.position.x >= End_pos.x || transform.position.x <= Start_pos.x)
        {
            flag = 0;
        }
        if (flag == 0)
        {
            if (waitTime > 0)
            {
                waitTime -= Time.deltaTime;
            }
            if (waitTime <= 0)
            {
                waitTime = WaitTime;
                if (transform.position.x >= End_pos.x)
                {
                    flag = -1;
                }
                else
                {
                    flag = 1;
                }
            }
        }
    }

    private void OnCollisionStay2D(Collision2D collision)
    {
        if (Rd.velocity.y <= 0)
        {
            flag = 2;
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (Rd.velocity.y < 0)
        {
            Fall_Music.Play();
            collider.isTrigger = false;
            Rd.velocity = new Vector3(0f, 0f, 0f);
        }
        else
        {
            collider.isTrigger = true;
        }
    }
}
