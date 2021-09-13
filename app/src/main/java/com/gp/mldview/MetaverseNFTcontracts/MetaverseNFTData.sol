pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import { MetaverseNFT } from "./MetaverseNFT.sol";


/**
 * @notice - This is the storage contract for metaverseNFTs
 */
contract MetaverseNFTData {

    Metaverse[] public metaverses;

    address[] public metaverseAddresses;

    constructor() public {}

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

    /**
     * @notice - Save metadata of a metaverseNFT
     */
    function saveMetadataOfMetaverseNFT(
        address[] memory _metaverseAddresses, 
        MetaverseNFT _metaverseNFT, 
        string memory _metaverseNFTName, 
        string memory _metaverseNFTSymbol, 
        address _ownerAddress, 
        uint _metaversePrice, 
        string memory _ipfsHashOfMetaverse, 
        //string memory _metaverseNftDescription,
        string memory _extra
    ) public returns (bool) {
        /// Save metadata of a metaverseNFT of metaverse
        Metaverse memory metaverse = Metaverse({
            metaverseNFT: _metaverseNFT,
            metaverseNFTName: _metaverseNFTName,
            metaverseNFTSymbol: _metaverseNFTSymbol,
            ownerAddress: _ownerAddress,
            metaversePrice: _metaversePrice,
            ipfsHashOfMetaverse: _ipfsHashOfMetaverse,
            status: "Open",
            reputation: 0, 
            //metaverseNftDescription: _metaverseNftDescription,
            extra: _extra
        });
        metaverses.push(metaverse);

        // Update metaverseAddresses
        metaverseAddresses = _metaverseAddresses;     
    }

    /**
     * @notice - Update owner address of a metaverseNFT by transferring ownership
     */
    function updateOwnerOfMetaverseNFT(MetaverseNFT _metaverseNFT, address _newOwner) public returns (bool) {
        /// Identify metaverse's index
        uint metaverseIndex = getMetaverseIndex(_metaverseNFT);

        /// Update metadata of a metaverseNFT of metaverse
        Metaverse storage metaverse = metaverses[metaverseIndex];
        require (_newOwner != address(0), "A new owner address should be not empty");
        metaverse.ownerAddress = _newOwner;  
    }

    /**
     * @notice - Update status ("Open" or "Cancelled")
     */
    function updateStatus(MetaverseNFT _metaverseNFT, string memory _newStatus) public returns (bool) {
        /// Identify metaverse's index
        //require( msg.sender == _metaverseNFT.owner);
        uint metaverseIndex = getMetaverseIndex(_metaverseNFT);

        /// Update metadata of a metaverseNFT of metaverse
        Metaverse storage metaverse = metaverses[metaverseIndex];
        metaverse.status = _newStatus;  
    }

    function updatePrice(MetaverseNFT _metaverseNFT, uint _price) public returns (bool) {
        //require( msg.sender == _metaverseNFT.owner);
        uint metaverseIndex = getMetaverseIndex(_metaverseNFT);
        /// Update metadata of a metaverseNFT of metaverse
        Metaverse storage metaverse = metaverses[metaverseIndex];
        metaverse.metaversePrice = _price; 
    }
    ///-----------------
    /// Getter methods
    ///-----------------
    function getMetaverse(uint index) public view returns (Metaverse memory _metaverse) {
        Metaverse memory metaverse = metaverses[index];
        return metaverse;
    }

    function getMetaverseIndex(MetaverseNFT metaverseNFT) public view returns (uint _metaverseIndex) {
        address METAVERSE_NFT = address(metaverseNFT);

        /// Identify member's index
        uint metaverseIndex;
        for (uint i=0; i < metaverseAddresses.length; i++) {
            if (metaverseAddresses[i] == METAVERSE_NFT) {
                metaverseIndex = i;
            }
        }

        return metaverseIndex;   
    }

    function getMetaverseByNFTAddress(MetaverseNFT metaverseNFT) public view returns (Metaverse memory _metaverse) {
        address METAVERSE_NFT = address(metaverseNFT);

        /// Identify member's index
        uint metaverseIndex;
        for (uint i=0; i < metaverseAddresses.length; i++) {
            if (metaverseAddresses[i] == METAVERSE_NFT) {
                metaverseIndex = i;
            }
        }

        Metaverse memory metaverse = metaverses[metaverseIndex];
        return metaverse;
    }

    function getMetaversesByRange(uint startIndex,uint blockSize) public view returns (Metaverse[] memory _metaverses) {
        
        require(startIndex > 0 && blockSize > 0 && (startIndex+blockSize)<=metaverseAddresses.length,"Range exceed");
        Metaverse[] memory metaversesRange;
        for (uint i=startIndex; i <= startIndex+blockSize; i++) {
            metaversesRange[i-startIndex] = metaverses[i];
        }
        return metaversesRange;
    }

    function getAllMetaverses() public view returns (Metaverse[] memory _metaverses) {
        return metaverses;
    }

    /*function getMyMetaverses() public view returns (Metaverse[] memory_metaverses) {
        for (uint i=0; i < metaverses.length; i++) {
            if (metaverses[i].ownerAddress == msg.sender) {
                
            }
        }
    }*/
}
