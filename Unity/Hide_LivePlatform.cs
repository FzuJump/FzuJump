using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hide_LivePlatform : MonoBehaviour
{
    public GameObject LivePlatform;
    public Collider2D collider;
    public Rigidbody2D Rd;
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionExit2D(Collision2D collision)
    {
        LivePlatform.SetActive(false);
    }
}
