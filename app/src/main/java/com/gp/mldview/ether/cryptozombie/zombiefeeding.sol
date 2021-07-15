pragma solidity ^0.4.19;

import "./zombiefactory.sol";

//猫合约接口
contract KittyInterface {
  function getKitty(uint256 _id) external view returns (
    bool isGestating,
    bool isReady,
    uint256 cooldownIndex,
    uint256 nextActionAt,
    uint256 siringWithId,
    uint256 birthTime,
    uint256 matronId,
    uint256 sireId,
    uint256 generation,
    uint256 genes
  );
}

//继承 ZombieFactory，也是个Ownable；  可以通过 Ownable 接口访问父类中的函数/事件/修饰符；
//也可以给 ZombieFeeding 加上 onlyOwner 函数修饰符
contract ZombieFeeding is ZombieFactory {

  KittyInterface kittyContract;

  //本人的僵尸校验
  modifier onlyOwnerOf(uint _zombieId) {
      require(msg.sender == zombieToOwner[_zombieId]);
      _;
  }

  //外部依赖
  // 首先执行 onlyOwner 中的代码， 执行到 onlyOwner 中的 _; 语句时，程序再返回本方法
  function setKittyContractAddress(address _address) external onlyOwner{
    kittyContract = KittyInterface(_address);
  }

  //确定下次readyTime
  function _triggerCooldown(Zombie storage _zombie) internal {
      _zombie.readyTime = uint32(now + cooldownTime);
  }

  //判断是否冷却时间过去
  function _isReady(Zombie storage _zombie) internal view returns (bool) {
      return (_zombie.readyTime <= now);
  }

  //捕食繁殖，自己的僵尸
  function feedAndMultiply(uint _zombieId, uint _targetDna, string species) internal onlyOwnerOf(_zombieId){
    //require(msg.sender == zombieToOwner[_zombieId]);
    Zombie storage myZombie = zombies[_zombieId];
    //是否ready
    require(_isReady(myZombie));
    //不长于16位
    _targetDna = _targetDna % dnaModulus;
    //猎食僵尸DNA和被猎僵尸DNA之间的平均值
    uint newDna = (myZombie.dna + _targetDna) / 2;
    //目前只使用16位DNA的前12位数来指定僵尸的外观
    //把猫僵尸DNA的最后两个数字设定为99
    if (keccak256(species) == keccak256("kitty")) {
      newDna = newDna - newDna % 100 + 99;
    }
    _createZombie("NoName", newDna);
    //触发了僵尸新的冷却周期
    _triggerCooldown(myZombie);
  }

  //捕食猫
  function feedOnKitty(uint _zombieId, uint _kittyId) public {
    uint kittyDna;
    (,,,,,,,,,kittyDna) = kittyContract.getKitty(_kittyId);
    feedAndMultiply(_zombieId, kittyDna, "kitty");
  }

}
