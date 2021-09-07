pragma solidity ^0.6.0;

import { MetaverseNFT } from "../../MetaverseNFT.sol";


contract MetaverseNFTFactoryEvents {

    event MetaverseNFTCreated (
        address owner,
        MetaverseNFT metaverseNFT,
        string nftName, 
        string nftSymbol, 
        uint metaversePrice, 
        string ipfsHashOfMetaverse,
        //string metaverseNftDescription
        //string tags,
        //string fileformat
        //string cover,
        string extra
    );

    event AddReputation (
        uint256 tokenId,
        uint256 reputationCount
    );

}
