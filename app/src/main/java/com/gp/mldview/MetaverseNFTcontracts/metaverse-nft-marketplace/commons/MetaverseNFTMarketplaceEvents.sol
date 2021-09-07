pragma solidity ^0.6.0;

import { MetaverseNFT } from "../../MetaverseNFT.sol";


contract MetaverseNFTMarketplaceEvents {

    event MetaverseNFTOwnershipChanged (
        MetaverseNFT metaverseNFT,
        uint metaverseId, 
        address ownerBeforeOwnershipTransferred,
        address ownerAfterOwnershipTransferred
    );

}
