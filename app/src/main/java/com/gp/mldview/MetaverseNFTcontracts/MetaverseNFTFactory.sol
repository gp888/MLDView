pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import { Strings } from "./openzeppelin-solidity/contracts/utils/Strings.sol";
import { MetaverseNFT } from "./MetaverseNFT.sol";
import { MetaverseNFTMarketplace } from "./MetaverseNFTMarketplace.sol";
import { MetaverseNFTData } from "./MetaverseNFTData.sol";


/**
 * @notice - This is the factory contract for a NFT of metaverse
 */
contract MetaverseNFTFactory {
    using Strings for string;

    event MetaverseNFTCreated (
         uint256 id,
         address owner,
         MetaverseNFT metaverseNFT,
         string nftName,
         string nftSymbol,
         uint metaversePrice,
         string ipfsHashOfMetaverse,
         string extra
    );

    event AddReputation (
        uint256 tokenId,
        uint256 reputationCount
    );

    MetaverseNFTData public metaverseNFTData;
    

    constructor(MetaverseNFTData _metaverseNFTData) public {
         metaverseNFTData = _metaverseNFTData;
    }

    /**
     * @notice - Create a new metaverseNFT when a seller (owner) upload a metaverse onto IPFS
     */
    function createNewMetaverseNFT(string memory nftName, string memory nftSymbol, uint metaversePrice, string memory ipfsHashOfMetaverse,string memory extra)
        public returns (bool) {
        address owner = msg.sender;
        string memory tokenURI = getTokenURI(ipfsHashOfMetaverse);  /// [Note]: IPFS hash + URL
        MetaverseNFT metaverseNFT = new MetaverseNFT(owner, nftName, nftSymbol, tokenURI);

        /// Save metadata of a metaverseNFT created
        metaverseNFTData.saveMetadataOfMetaverseNFT(address(metaverseNFT), metaverseNFT, nftName, nftSymbol, msg.sender, metaversePrice, ipfsHashOfMetaverse, extra);
        metaverseNFTData.updateStatus(metaverseNFT, "Cancelled");


        emit MetaverseNFTCreated(metaverseNFTData.metaverses.length - 1, msg.sender, metaverseNFT, nftName, nftSymbol, metaversePrice, ipfsHashOfMetaverse,extra);
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
