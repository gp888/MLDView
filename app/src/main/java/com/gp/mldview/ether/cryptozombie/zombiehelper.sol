pragma solidity ^0.4.19;

import "./zombiefeeding.sol";

contract ZombieHelper is ZombieFeeding {

    uint levelUpFee = 0.001 ether;

    //等级限制
    modifier aboveLevel(uint _level, uint _zombieId) {
        require(zombies[_zombieId].level >= _level);
        //表示修饰符调用结束后返回，并执行调用函数余下的部分。
        _;
    }

    //提现合约余额
    function withdraw() external onlyOwner {
        owner.transfer(this.balance);
    }

    //设置僵尸升级费用
    function setLevelUpFee(uint _fee) external onlyOwner {
        levelUpFee = _fee;
    }

    //充钱升级僵尸
    function levelUp(uint _zombieId) external payable {
        require(msg.value == levelUpFee);
        zombies[_zombieId].level = zombies[_zombieId].level.add(1);
    }

    //两级以上可以改名字
    function changeName(uint _zombieId, string _newName) external aboveLevel(2, _zombieId) onlyOwnerOf(_zombieId){
        //require(msg.sender == zombieToOwner[_zombieId]);
        zombies[_zombieId].name = _newName;
      }

    //20级以上可以改dna
    function changeDna(uint _zombieId, uint _newDna) external aboveLevel(20, _zombieId) onlyOwnerOf(_zombieId){
        //require(msg.sender == zombieToOwner[_zombieId]);
        zombies[_zombieId].dna = _newDna;
    }

    //查询用户的僵尸数组
    function getZombiesByOwner(address _owner) external view returns(uint[]) {
        uint[] memory result = new uint[](ownerZombieCount[_owner]);

        uint counter = 0;
        for (uint i = 0; i < zombies.length; i++) {
            if (zombieToOwner[i] == _owner) {
                 result[counter] = i;
                 counter++;
            }
        }
        return result;
    }
}
