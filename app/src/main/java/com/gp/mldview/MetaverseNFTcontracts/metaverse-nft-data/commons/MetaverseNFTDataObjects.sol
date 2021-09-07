pragma solidity ^0.6.0;

import { MetaverseNFT } from "../../MetaverseNFT.sol";


contract MetaverseNFTDataObjects {

    struct Metaverse {  /// [Key]: index of array
        MetaverseNFT metaverseNFT;
        string metaverseNFTName;
        string metaverseNFTSymbol;
        address ownerAddress;
        uint metaversePrice;
        string ipfsHashOfMetaverse;
        string status;  /// "Open" or "Cancelled"
        uint256 reputation;
        //string metaverseNftDescription;
        //string fileformat; //fileformat ".mp4" 会被定义为mp4
        string extra; //extra description
        //string tags;  //"split by #"
        //string cover; //cover of nft
    }


}


