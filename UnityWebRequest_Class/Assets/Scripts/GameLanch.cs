using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameLanch : MonoBehaviour
{
    private void Awake()
    {
        //初始化游戏框架：资源管理、声音管理。。。。。。网络管理
        //end

        this.gameObject.AddComponent<GameApp>();

        this.GameStart();
    }

    public void GameStart()
    {
        //检查资源更新
        //end

        //进入我们的游戏里面
        this.gameObject.GetComponent<GameApp>().EnterGame();
        //end
    }
}
