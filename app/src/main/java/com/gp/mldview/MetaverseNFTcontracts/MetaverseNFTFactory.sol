pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import { SafeMath } from "./openzeppelin-solidity/contracts/math/SafeMath.sol";
import { Strings } from "./libraries/Strings.sol";
import { MetaverseNFTFactoryStorages } from "./metaverse-nft-factory/commons/MetaverseNFTFactoryStorages.sol";
import { MetaverseNFT } from "./MetaverseNFT.sol";
import { MetaverseNFTMarketplace } from "./MetaverseNFTMarketplace.sol";
import { MetaverseNFTData } from "./MetaverseNFTData.sol";


/**
 * @notice - This is the factory contract for a NFT of metaverse
 */
contract MetaverseNFTFactory is MetaverseNFTFactoryStorages {
    using SafeMath for uint256;
    using Strings for string;    

    address[] public metaverseAddresses;
    address METAVERSE_NFT_MARKETPLACE;

    MetaverseNFTMarketplace public metaverseNFTMarketplace;
    MetaverseNFTData public metaverseNFTData;

    constructor(MetaverseNFTMarketplace _metaverseNFTMarketplace, MetaverseNFTData _metaverseNFTData) public {
        metaverseNFTMarketplace = _metaverseNFTMarketplace;
        metaverseNFTData = _metaverseNFTData;
        METAVERSE_NFT_MARKETPLACE = address(metaverseNFTMarketplace);
    }

    /**
     * @notice - Create a new metaverseNFT when a seller (owner) upload a metaverse onto IPFS
     */
    function createNewMetaverseNFT(string memory nftName, string memory nftSymbol, uint metaversePrice, string memory ipfsHashOfMetaverse,string memory extra)
        public returns (bool) {
        address owner = msg.sender;  /// [Note]: Initial owner of metaverseNFT is msg.sender
        string memory tokenURI = getTokenURI(ipfsHashOfMetaverse);  /// [Note]: IPFS hash + URL
        MetaverseNFT metaverseNFT = new MetaverseNFT(owner, nftName, nftSymbol, tokenURI, metaversePrice);
        metaverseAddresses.push(address(metaverseNFT));

        /// Save metadata of a metaverseNFT created
        metaverseNFTData.saveMetadataOfMetaverseNFT(metaverseAddresses, metaverseNFT, nftName, nftSymbol, msg.sender, metaversePrice, ipfsHashOfMetaverse,extra);
        metaverseNFTData.updateStatus(metaverseNFT, "Cancelled");

        emit MetaverseNFTCreated(msg.sender, metaverseNFT, nftName, nftSymbol, metaversePrice, ipfsHashOfMetaverse,extra);
    }

    ///-----------------
    /// Getter methods
    ///-----------------
    function baseTokenURI() public pure returns (string memory) {
        return "https://ipfs.io/ipfs/";
    }

    function getTokenURI(string memory _ipfsHashOfMetaverse) public view returns (string memory) {
        return Strings.strConcat(baseTokenURI(), _ipfsHashOfMetaverse);
    }

}
