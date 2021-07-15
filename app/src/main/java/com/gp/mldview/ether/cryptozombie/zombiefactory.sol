pragma solidity ^0.4.19;

import "./ownable.sol";
import "./safemath.sol";

//继承 Ownable
contract ZombieFactory is Ownable {

    using SafeMath for uint256;
    using SafeMath32 for uint32;
    using SafeMath16 for uint16;

    //事件
    event NewZombie(uint zombieId, string name, uint dna);

    // 这个无符号整数将会永久的被保存在区块链中
    uint dnaDigits = 16;
    //乘方
    uint dnaModulus = 10 ** dnaDigits;

    uint cooldownTime = 1 days;

    struct Zombie {
        string name;
        //16位整数组成 8356281049284737
        uint dna;

        uint32 level;
        //猎食冷却器
        uint32 readyTime;

        uint16 winCount;
        uint16 lossCount;
    }

    //保存所有的僵尸
    //public 数组, Solidity 会自动创建 getter 方法
    //其它的合约可以从这个数组读取数据（但不能写入数据），所以这在合约中是一个有用的保存公共数据的模式
    Zombie[] public zombies;

    //zobmie Id 映射主人address
    mapping (uint => address) public zombieToOwner;
    //主人address 映射 zombie数量
    mapping (address => uint) ownerZombieCount;

    //真正生成僵尸
    function _createZombie(string _name, uint _dna) internal {
        //array.push() 返回数组的长度类型是uint - 因为数组的第一个元素的索引是 0， array.push() - 1 将是我们加入的僵尸的索引。
        //zombies.push() - 1 就是 id，数据类型是 uint。
        uint id = zombies.push(Zombie(_name, _dna, 1, uint32(now + cooldownTime), 0, 0)) - 1;
        zombieToOwner[id] = msg.sender;
        ownerZombieCount[msg.sender] = ownerZombieCount[msg.sender].add(1);
        NewZombie(id, _name, _dna);
    }

    //私有函数的名字用(_)起始
    //随机生成dna，不超过16位
    function _generateRandomDna(string _str) private view returns (uint) {
        //散列函数keccak256,把一个字符串转换为一个256位的16进制数字
        //类型转换为 uint
        uint rand = uint(keccak256(_str));
        //不超过16位
        return rand % dnaModulus;
    }

    //创造新僵尸
    function createRandomZombie(string _name) public {
        require(ownerZombieCount[msg.sender] == 0);
        uint randDna = _generateRandomDna(_name);
        //去掉最后两位
        randDna = randDna - randDna % 100;
        _createZombie(_name, randDna);
    }

}
