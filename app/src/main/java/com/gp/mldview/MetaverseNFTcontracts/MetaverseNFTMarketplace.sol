pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

//import { ERC20 } from './openzeppelin-solidity/contracts/token/ERC20/ERC20.sol';
import { SafeMath } from "./openzeppelin-solidity/contracts/math/SafeMath.sol";
import { MetaverseNFT } from "./MetaverseNFT.sol";
import { MetaverseNFTTradable } from "./MetaverseNFTTradable.sol";
import { MetaverseNFTMarketplaceEvents } from "./metaverse-nft-marketplace/commons/MetaverseNFTMarketplaceEvents.sol";
import { MetaverseNFTData } from "./MetaverseNFTData.sol";


contract MetaverseNFTMarketplace is MetaverseNFTTradable, MetaverseNFTMarketplaceEvents {
    using SafeMath for uint256;

    address public METAVERSE_NFT_MARKETPLACE;

    MetaverseNFTData public metaverseNFTData;

    constructor(MetaverseNFTData _metaverseNFTData) public MetaverseNFTTradable(_metaverseNFTData) {
        metaverseNFTData = _metaverseNFTData;
        address payable METAVERSE_NFT_MARKETPLACE = address(uint160(address(this)));
    }

    /** 
     * @notice - Buy function is that buy NFT token and ownership transfer. (Reference from IERC721.sol)
     * @notice - msg.sender buy NFT with ETH (msg.value)
     * @notice - MetaverseID is always 1. Because each metaverseNFT is unique.
     */
    function buyMetaverseNFT(MetaverseNFT _metaverseNFT) public payable returns (bool) {
        MetaverseNFT metaverseNFT = _metaverseNFT;

        MetaverseNFTData.Metaverse memory metaverse = metaverseNFTData.getMetaverseByNFTAddress(metaverseNFT);
        address _seller = metaverse.ownerAddress;                     /// Owner
        address payable seller = address(uint160(_seller));  /// Convert owner address with payable
        uint buyAmount = metaverse.metaversePrice;
        require (msg.value == buyAmount, "msg.value should be equal to the buyAmount");
 
        /// Bought-amount is transferred into a seller wallet
        seller.transfer(buyAmount);

        /// Approve a buyer address as a receiver before NFT's transferFrom method is executed
        address buyer = msg.sender;
        uint metaverseId = 1;  /// [Note]: MetaverseID is always 1. Because each metaverseNFT is unique.
        metaverseNFT.approve(buyer, metaverseId);

        address ownerBeforeOwnershipTransferred = metaverseNFT.ownerOf(metaverseId);

        /// Transfer Ownership of the MetaverseNFT from a seller to a buyer
        transferOwnershipOfMetaverseNFT(metaverseNFT, metaverseId, buyer);    
        metaverseNFTData.updateOwnerOfMetaverseNFT(metaverseNFT, buyer);
        metaverseNFTData.updateStatus(metaverseNFT, "Cancelled");

        /// Event for checking result of transferring ownership of a metaverseNFT
        address ownerAfterOwnershipTransferred = metaverseNFT.ownerOf(metaverseId);
        emit MetaverseNFTOwnershipChanged(metaverseNFT, metaverseId, ownerBeforeOwnershipTransferred, ownerAfterOwnershipTransferred);

        /// Mint a metaverse with a new metaverseId
        //string memory tokenURI = metaverseNFTFactory.getTokenURI(metaverseData.ipfsHashOfMetaverse);  /// [Note]: IPFS hash + URL
        //metaverseNFT.mint(msg.sender, tokenURI);
    }


    ///-----------------------------------------------------
    /// Methods below are pending methods
    ///-----------------------------------------------------

    /** 
     * @dev reputation function is that gives reputation to a user who has ownership of being posted metaverse.
     * @dev Each user has reputation data in struct
     */
    function reputation(address from, address to, uint256 metaverseId) public returns (uint256, uint256) {

        // Metaverse storage metaverse = metaverses[metaverseId];
        // metaverse.reputation = metaverse.reputation.add(1);

        // emit AddReputation(metaverseId, metaverse.reputation);

        // return (metaverseId, metaverse.reputation);
        return (0, 0);
    }
    

    function getReputationCount(uint256 metaverseId) public view returns (uint256) {
        uint256 curretReputationCount;

        // Metaverse memory metaverse = metaverses[metaverseId];
        // curretReputationCount = metaverse.reputation;

        return curretReputationCount;
    }    

}
